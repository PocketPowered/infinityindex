package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.local.Story
import kotlinx.coroutines.flow.StateFlow

interface PagingDataConsumerScreenState {
    val hasLoaded: StateFlow<Boolean>
    val entityCountsData: StateFlow<EntityCountsData>
    val characterData: StateFlow<PagingData<Character>>
    val creatorsData: StateFlow<PagingData<Creator>>
    val eventsData: StateFlow<PagingData<Event>>
    val storiesData: StateFlow<PagingData<Story>>
    val seriesData: StateFlow<PagingData<Series>>
    val comicData: StateFlow<PagingData<Comic>>
}

data class EntityCountsData(
    val charactersCount: Long? = null,
    val creatorsCount: Long? = null,
    val eventsCount: Long? = null,
    val storiesCount: Long? = null,
    val seriesCount: Long? = null,
    val comicCount: Long? = null
)