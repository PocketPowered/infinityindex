package com.wongislandd.infinityindex.browse

import com.wongislandd.infinityindex.infra.viewmodels.EntityPagingData
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState
import kotlinx.coroutines.flow.StateFlow

data class BrowseScreenState(
    val isBrowseScreenLoading: Boolean,
    val errorData: String? = null,
    override val characterData: StateFlow<EntityPagingData>,
    override val creatorsData: StateFlow<EntityPagingData>,
    override val eventsData: StateFlow<EntityPagingData>,
    override val storiesData: StateFlow<EntityPagingData>,
    override val seriesData: StateFlow<EntityPagingData>,
    override val comicData: StateFlow<EntityPagingData>,
) : PagingDataConsumerScreenState