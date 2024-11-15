package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.cash.paging.Pager
import com.wongislandd.infinityindex.infra.ListBackChannelEvent
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.paging.EntityPagingSource
import com.wongislandd.infinityindex.infra.paging.PaginationContextWrapper
import com.wongislandd.infinityindex.infra.paging.PagingSourceCallbacks
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SortOption
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.getDefaultSortOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseListPagingSlice<NETWORK_TYPE, LOCAL_TYPE : EntityModel>(
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
    private val entityType: EntityType
) : ViewModelSlice() {

    private var currentPagingSource: EntityPagingSource<NETWORK_TYPE, LOCAL_TYPE>? = null
    private var currentRefreshWatcherJob: Job? = null
    private var currentSearchQuery: String? = null
    private var currentSortOption: SortOption = entityType.getDefaultSortOption()

    private var pagingConfig = PagingConfig(
        initialLoadSize = 40,
        pageSize = 20,
        enablePlaceholders = false,
        prefetchDistance = 10
    )

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ListBackChannelEvent.SubmitSearchQuery -> updatePagingParameters(
                searchQuery = event.query,
            )

            is ListBackChannelEvent.SubmitSortSelection -> updatePagingParameters(
                sortOption = event.sortOption
            )
        }
    }

    fun setPagingConfig(newPagingConfig: PagingConfig) {
        pagingConfig = newPagingConfig
    }

    override fun afterInit() {
        initializePaging()
    }

    private fun initializePaging() {
        sliceScope.launch {
            Pager(
                config = pagingConfig
            ) {
                val newPagingSource = EntityPagingSource(
                    repository = repository,
                    searchQuery = currentSearchQuery,
                    sortOption = currentSortOption
                ).also { currentPagingSource = it }
                currentRefreshWatcherJob?.cancel()
                currentRefreshWatcherJob = CoroutineScope(Dispatchers.Main).launch {
                    newPagingSource.isFetchingFirstPage.collectLatest {
                        backChannelEvents.sendEvent(
                            ListBackChannelEvent.PagingRefreshingUpdate(it)
                        )
                    }
                }
                newPagingSource.registerOnSuccessListener(object : PagingSourceCallbacks<LOCAL_TYPE> {
                    override fun onSuccess(paginationContextWrapper: PaginationContextWrapper<LOCAL_TYPE>) {
                        sliceScope.launch {
                            backChannelEvents.sendEvent(
                                ListBackChannelEvent.EntityCountsUpdate(
                                    totalCount = paginationContextWrapper.total,
                                    entityType = entityType
                                )
                            )
                        }
                    }
                })
                newPagingSource
            }.flow.cachedIn(sliceScope).collectLatest {
                backChannelEvents.sendEvent(
                    ListBackChannelEvent.PagingDataResUpdate(it, entityType)
                )
            }
        }
    }


    private fun updatePagingParameters(
        searchQuery: String? = null,
        sortOption: SortOption? = null
    ) {
        if (searchQuery == null && sortOption == null) return
        searchQuery?.also {
            currentSearchQuery = searchQuery
        }
        sortOption?.also {
            currentSortOption = sortOption
        }
        currentPagingSource?.invalidate()
    }

}