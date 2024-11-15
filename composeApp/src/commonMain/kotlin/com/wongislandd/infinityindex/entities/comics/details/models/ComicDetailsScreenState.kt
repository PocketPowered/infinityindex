package com.wongislandd.infinityindex.entities.comics.details.models

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.PillarModel
import com.wongislandd.infinityindex.infra.util.Resource
import kotlinx.coroutines.flow.StateFlow

// nothing will flow through the non-primary res data, wondering if we can
// make the types of other paging data variable
data class BaseDetailsScreenState<T: PillarModel>(
    val primaryId: Int? = null,
    val primaryRes: Resource<T> = Resource.Loading,
    val characterData: StateFlow<PagingData<Character>>,
    val creatorsData: StateFlow<PagingData<Creator>>,
    val eventsData: StateFlow<PagingData<Event>>,
    val storiesData: StateFlow<PagingData<Story>>,
    val seriesData: StateFlow<PagingData<Series>>,
    val comicData: StateFlow<PagingData<Comic>>
)