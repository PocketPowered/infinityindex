package com.wongislandd.infinityindex.infra.viewmodels

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.entities.comics.list.models.SearchIntention
import com.wongislandd.infinityindex.entities.comics.list.models.SearchQuery
import com.wongislandd.infinityindex.entities.comics.list.models.SearchState
import com.wongislandd.infinityindex.infra.ListBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.getDefaultSortOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseListScreenStateSlice<T : EntityModel>(
    entityType: EntityType
) :
    BaseScreenStateSlice<T>, ViewModelSlice() {

    private val listPagingData: MutableStateFlow<PagingData<EntityModel>> =
        MutableStateFlow(PagingData.empty())

    private val _screenState: MutableStateFlow<BaseListScreenState> =
        MutableStateFlow(
            BaseListScreenState(
                isLoading = true,
                sortOption = entityType.getDefaultSortOption(),
                searchState = SearchState(
                    searchQuery = SearchQuery("", SearchIntention.PENDING),
                    isSearchBoxVisible = false
                ),
                pagingData = listPagingData
            )
        )

    val screenState: StateFlow<BaseListScreenState> = _screenState

    @Suppress("UNCHECKED_CAST")
    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ListBackChannelEvent.PagingDataResUpdate<*> -> {
                listPagingData.value = event.update as PagingData<EntityModel>
            }

            is ListBackChannelEvent.PagingRefreshingUpdate -> {
                _screenState.value = _screenState.value.copy(isLoading = event.refreshing)
            }

            is ListBackChannelEvent.UpdateSearchBoxVisibility -> {
                _screenState.value = _screenState.value.copy(
                    searchState = _screenState.value.searchState.copy(
                        isSearchBoxVisible = event.isVisible
                    )
                )
            }

            is ListBackChannelEvent.UpdatePendingSearchQuery -> {
                _screenState.value = _screenState.value.copy(
                    searchState = _screenState.value.searchState.copy(
                        searchQuery = SearchQuery(event.query, SearchIntention.PENDING)
                    )
                )
            }

            is ListBackChannelEvent.SubmitSortSelection -> {
                _screenState.value = _screenState.value.copy(sortOption = event.sortOption)
            }
        }
    }
}