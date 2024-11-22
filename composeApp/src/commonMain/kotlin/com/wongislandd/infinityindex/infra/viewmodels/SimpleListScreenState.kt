package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.entities.comics.models.SearchState
import com.wongislandd.infinityindex.infra.util.SelectableSortOption

interface BaseListScreenState {
    val availableSortOptions: List<SelectableSortOption>
    val searchState: SearchState
    val isDigitallyAvailableFilterEnabled: Boolean
    val isVariantsEnabled: Boolean
}

data class SimpleListScreenState(
    override val availableSortOptions: List<SelectableSortOption>,
    override val searchState: SearchState,
    override val isDigitallyAvailableFilterEnabled: Boolean,
    override val isVariantsEnabled: Boolean
) : BaseListScreenState