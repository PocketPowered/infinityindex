package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.cash.paging.Pager
import com.wongislandd.infinityindex.infra.ListBackChannelEvent
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.paging.EntityPagingSource
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
    entityType: EntityType
) : ViewModelSlice() {

    private var currentPagingSource: EntityPagingSource<NETWORK_TYPE, LOCAL_TYPE>? = null
    private var currentRefreshWatcherJob: Job? = null
    private var currentSearchQuery: String? = null
    private var currentSortOption: SortOption = entityType.getDefaultSortOption()

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

    override fun afterInit() {
        initializePaging()
    }

    private fun initializePaging() {
        sliceScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    prefetchDistance = 20
                )
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
                newPagingSource
            }.flow.cachedIn(sliceScope).collectLatest {
                backChannelEvents.sendEvent(
                    ListBackChannelEvent.PagingDataResUpdate(it)
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