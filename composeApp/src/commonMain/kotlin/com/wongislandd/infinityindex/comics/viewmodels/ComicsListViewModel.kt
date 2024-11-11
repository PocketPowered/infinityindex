package com.wongislandd.infinityindex.comics.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.comics.data.ComicsPagingSource
import com.wongislandd.infinityindex.comics.data.ComicsRepository
import com.wongislandd.infinityindex.comics.models.ComicsSortOption
import com.wongislandd.infinityindex.comics.models.NetworkComic
import com.wongislandd.infinityindex.comics.util.ComicConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

enum class SearchIntention {
    PENDING,
    SUBMITTED
}

data class SearchQuery(
    val text: String = "",
    val intention: SearchIntention = SearchIntention.PENDING
)

data class SearchState(
    val searchQuery: SearchQuery,
    val isSearchBoxVisible: Boolean
)

data class ComicsListScreenState(
    val isLoading: Boolean,
    val sortOption: ComicsSortOption,
    val searchState: SearchState,
    val pagingData: StateFlow<PagingData<NetworkComic>>
)

class ComicsListViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {

    private val pagingData: MutableStateFlow<PagingData<NetworkComic>> =
        MutableStateFlow(PagingData.empty())

    // screen state
    private val _screenState: MutableStateFlow<ComicsListScreenState> = MutableStateFlow(
        ComicsListScreenState(
            isLoading = true,
            sortOption = ComicConstants.DEFAULT_SORT_OPTION,
            searchState = SearchState(
                searchQuery = SearchQuery("", SearchIntention.PENDING),
                isSearchBoxVisible = false
            ),
            pagingData = pagingData
        )
    )
    val screenState = _screenState.asStateFlow()

    private var currentPagingSource: ComicsPagingSource? = null
    private var currentRefreshWatcherJob: Job? = null

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    initialLoadSize = 40,
                    pageSize = 20,
                    enablePlaceholders = false,
                    prefetchDistance = 10
                )
            ) {
                val newPagingSource = ComicsPagingSource(
                    comicsRepository = comicsRepository,
                    searchQuery = if (_screenState.value.searchState.searchQuery.isQueryable()) {
                        _screenState.value.searchState.searchQuery
                    } else {
                        null
                    },
                    sortOption = _screenState.value.sortOption
                ).also { currentPagingSource = it }
                // Watch for refreshing state
                currentRefreshWatcherJob?.cancel()
                currentRefreshWatcherJob = CoroutineScope(Dispatchers.Main).launch {
                    newPagingSource.isFetchingFirstPage.collectLatest {
                        _screenState.value = _screenState.value.copy(isLoading = it)
                    }
                }
                newPagingSource
            }.flow.collectLatest {
                pagingData.value = it
            }
        }
    }

    private fun SearchQuery.isQueryable() =
        this.text.isNotBlank() && this.intention == SearchIntention.SUBMITTED

    fun setSortOption(sortOption: ComicsSortOption) {
        _screenState.value = _screenState.value.copy(
            sortOption = sortOption
        )
        currentPagingSource?.invalidate()
    }

    fun setPendingSearchQuery(query: String) {
        _screenState.value = _screenState.value.copy(
            searchState = _screenState.value.searchState.copy(
                searchQuery = SearchQuery(query, SearchIntention.PENDING)
            )
        )
    }

    fun submitSearchQuery(query: String) {
        _screenState.value = _screenState.value.copy(
            searchState = _screenState.value.searchState.copy(
                searchQuery = SearchQuery(query, SearchIntention.SUBMITTED),
                isSearchBoxVisible = false
            )
        )
        currentPagingSource?.invalidate()
    }

    fun setSearchBoxVisibility(isVisible: Boolean) {
        _screenState.value = _screenState.value.copy(
            searchState = _screenState.value.searchState.copy(
                isSearchBoxVisible = isVisible
            )
        )
    }

}