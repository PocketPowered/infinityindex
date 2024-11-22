package com.wongislandd.infinityindex.home

import androidx.paging.PagingData
import com.wongislandd.infinityindex.infra.viewmodels.EntityCountsData
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.local.Story
import kotlinx.coroutines.flow.StateFlow

data class HomeScreenState(
    val isHomeScreenLoading: Boolean,
    val errorData: String? = null,
    override val characterData: StateFlow<PagingData<Character>>,
    override val creatorsData: StateFlow<PagingData<Creator>>,
    override val eventsData: StateFlow<PagingData<Event>>,
    override val storiesData: StateFlow<PagingData<Story>>,
    override val seriesData: StateFlow<PagingData<Series>>,
    override val comicData: StateFlow<PagingData<Comic>>,
    override val entityCountsData: StateFlow<EntityCountsData>,
) : PagingDataConsumerScreenState