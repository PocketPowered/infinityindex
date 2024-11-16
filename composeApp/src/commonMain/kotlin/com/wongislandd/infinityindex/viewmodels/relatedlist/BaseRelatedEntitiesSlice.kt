package com.wongislandd.infinityindex.viewmodels.relatedlist

import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.ListBackChannelEvent
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.paging.PaginationContextWrapper
import com.wongislandd.infinityindex.infra.paging.PagingSourceCallbacks
import com.wongislandd.infinityindex.infra.paging.RelatedEntityPagingSource
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Root Entity Type == If I want to see all characters in a comic,
 * the comic is the root type and the related type is the characters
 */
abstract class BaseRelatedEntitiesSlice<NETWORK_TYPE, LOCAL_TYPE : EntityModel>(
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
    private val entityType: EntityType,
) : ViewModelSlice() {

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is DetailsBackChannelEvent.RequestForPagination -> {
                if (event.relatedEntityTypeToPageFor == entityType) {
                    launchRelatedPagingFlow(event.rootEntityId, event.rootEntityType)
                }
            }
        }
    }

    private fun launchRelatedPagingFlow(primaryEntityId: Int, relatedEntityType: EntityType) {
        sliceScope.launch {
            Pager(
                config = PagingConfig(
                    initialLoadSize = 5,
                    pageSize = 5,
                    enablePlaceholders = false,
                    prefetchDistance = 2
                )
            ) {
                val pagingSource =
                    RelatedEntityPagingSource(repository, relatedEntityType, primaryEntityId)
                // This is likely a memory leak when the source gets invalidated, look to clean up
                pagingSource.registerCallbacks(object : PagingSourceCallbacks {
                    override fun onResponse(response: Resource<DataWrapper<*>>) {
                        sliceScope.launch {
                            backChannelEvents.sendEvent(
                                ListBackChannelEvent.EntityResponseReceived(
                                    entityType
                                )
                            )
                        }
                    }

                    override fun onSuccess(paginationContextWrapper: PaginationContextWrapper<*>) {
                        sliceScope.launch {
                            backChannelEvents.sendEvent(
                                ListBackChannelEvent.EntityCountsUpdate(
                                    totalCount = paginationContextWrapper.total,
                                    entityType = entityType
                                )
                            )
                        }
                    }

                    override fun onFailure(error: Throwable?) {
                        // do nothing
                    }
                })
                pagingSource
            }.flow.cachedIn(sliceScope).collectLatest {
                backChannelEvents.sendEvent(
                    ListBackChannelEvent.PagingDataResUpdate(it, entityType)
                )
            }
        }
    }
}