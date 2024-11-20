package com.wongislandd.infinityindex.infra.viewmodels

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.entities.comics.models.SearchIntention
import com.wongislandd.infinityindex.entities.comics.models.SearchQuery
import com.wongislandd.infinityindex.entities.comics.models.SearchState
import com.wongislandd.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SelectableSortOption
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.getSortOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseListScreenStateSlice<T : EntityModel>(
    val entityType: EntityType
) : BaseScreenStateSlice<T>, ViewModelSlice() {

    private val _listPagingData: MutableStateFlow<PagingData<EntityModel>> =
        MutableStateFlow(PagingData.empty())

    private val _screenState: MutableStateFlow<BaseListScreenState> =
        MutableStateFlow(
            BaseListScreenState(
                availableSortOptions = entityType.getSortOptions()
                    .map { SelectableSortOption(it, it.isDefault) },
                searchState = SearchState(
                    searchQuery = SearchQuery("", SearchIntention.PENDING),
                    isSearchBoxVisible = false
                )
            )
        )

    val listPagingData: StateFlow<PagingData<EntityModel>> = _listPagingData

    val screenState: StateFlow<BaseListScreenState> = _screenState

    @Suppress("UNCHECKED_CAST")
    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is PagingBackChannelEvent.PagingDataResUpdate<*> -> {
                _listPagingData.value = event.update as PagingData<EntityModel>
            }

            is PagingBackChannelEvent.UpdateSearchBoxVisibility -> {
                _screenState.update {
                    it.copy(
                        searchState = it.searchState.copy(
                            isSearchBoxVisible = event.isVisible
                        )
                    )
                }
            }

            is PagingBackChannelEvent.UpdatePendingSearchQuery -> {
                _screenState.update {
                    it.copy(
                        searchState = it.searchState.copy(
                            searchQuery = SearchQuery(event.query, SearchIntention.PENDING)
                        )
                    )
                }
            }

            is PagingBackChannelEvent.SubmitSortSelection -> {
                _screenState.update {
                    val newSortOptions = it.availableSortOptions.map { selectableSortOption ->
                        selectableSortOption.copy(
                            isSelected = selectableSortOption.sortOption == event.sortOption
                        )
                    }
                    it.copy(availableSortOptions = newSortOptions)
                }
            }
        }
    }
}