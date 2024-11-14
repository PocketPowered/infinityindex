package com.wongislandd.infinityindex.entities.comics.details.models

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import kotlinx.coroutines.flow.StateFlow

// nothing will flow through the non-primary res data, wondering if we can
// make the types of other paging data variable
data class BaseDetailsScreenState<PillarModel>(
    val primaryId: Int? = null,
    val primaryRes: Resource<PillarModel> = Resource.Loading,
    val characterData: StateFlow<PagingData<Character>>,
    val creatorsData: StateFlow<PagingData<Creator>>,
    val eventsData: StateFlow<PagingData<ComicEvent>>,
    val storiesData: StateFlow<PagingData<Story>>,
    val seriesData: StateFlow<PagingData<Series>>,
    val comicData: StateFlow<PagingData<Comic>>
)

data class ComicDetailsScreenState(
    val comicId: Int? = null,
    val comicRes: Resource<Comic> = Resource.Loading,
    val characterData: StateFlow<PagingData<Character>>,
    val creatorsData: StateFlow<PagingData<Creator>>,
    val eventsData: StateFlow<PagingData<ComicEvent>>,
    val storiesData: StateFlow<PagingData<Story>>
)