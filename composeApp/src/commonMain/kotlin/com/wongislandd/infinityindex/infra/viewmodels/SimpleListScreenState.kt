package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.models.util.SearchState
import com.wongislandd.infinityindex.infra.util.SelectableSortOption

data class ListState(
    val availableSortOptions: List<SelectableSortOption>,
    val searchState: SearchState
)

open class BaseListScreenState(
    open val listState: ListState
)