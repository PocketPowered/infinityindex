package com.wongislandd.infinityindex.infra.viewmodels

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.entities.comics.list.models.SearchState
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.SortOption
import kotlinx.coroutines.flow.StateFlow

data class BaseListScreenState(
    val isLoading: Boolean = true,
    val sortOption: SortOption,
    val searchState: SearchState,
    val pagingData: StateFlow<PagingData<EntityModel>>
)