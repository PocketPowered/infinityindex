package com.wongislandd.infinityindex.entities.comics.details.models

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import kotlinx.coroutines.flow.StateFlow

data class ComicDetailsScreenState(
    val comicId: Int? = null,
    val comicRes: Resource<Comic> = Resource.Loading,
    val characterData: StateFlow<PagingData<Character>>,
    val creatorsData: StateFlow<PagingData<Creator>>,
    val seriesData: StateFlow<PagingData<Series>>,
    val eventsData: StateFlow<PagingData<ComicEvent>>,
    val storiesData: StateFlow<PagingData<Story>>
)