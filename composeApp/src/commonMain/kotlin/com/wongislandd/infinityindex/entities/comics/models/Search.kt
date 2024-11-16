package com.wongislandd.infinityindex.entities.comics.models

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