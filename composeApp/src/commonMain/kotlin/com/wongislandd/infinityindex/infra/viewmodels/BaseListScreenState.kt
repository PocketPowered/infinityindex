package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.entities.comics.models.SearchState
import com.wongislandd.infinityindex.infra.util.SelectableSortOption

data class BaseListScreenState(
    val availableSortOptions: List<SelectableSortOption>,
    val searchState: SearchState,
)