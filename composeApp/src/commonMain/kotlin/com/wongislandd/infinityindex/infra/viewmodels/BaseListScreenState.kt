package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.entities.comics.list.models.SearchState
import com.wongislandd.infinityindex.infra.util.SelectableSortOption

data class BaseListScreenState(
    val isLoading: Boolean = false,
    val availableSortOptions: List<SelectableSortOption>,
    val searchState: SearchState,
)