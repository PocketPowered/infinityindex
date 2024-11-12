package com.wongislandd.infinityindex.pillars.comics.list.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow

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
    val pagingData: StateFlow<PagingData<BasicComic>>
)