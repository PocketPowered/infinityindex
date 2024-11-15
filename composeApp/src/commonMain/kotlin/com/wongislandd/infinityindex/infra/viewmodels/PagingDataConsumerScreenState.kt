package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import kotlinx.coroutines.flow.StateFlow

interface PagingDataConsumerScreenState {
    val characterData: StateFlow<PagingData<Character>>
    val creatorsData: StateFlow<PagingData<Creator>>
    val eventsData: StateFlow<PagingData<Event>>
    val storiesData: StateFlow<PagingData<Story>>
    val seriesData: StateFlow<PagingData<Series>>
    val comicData: StateFlow<PagingData<Comic>>
}