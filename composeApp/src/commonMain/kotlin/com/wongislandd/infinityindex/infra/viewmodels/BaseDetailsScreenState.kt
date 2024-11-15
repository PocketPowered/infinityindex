package com.wongislandd.infinityindex.infra.viewmodels

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import kotlinx.coroutines.flow.StateFlow

// nothing will flow through the non-primary res data, wondering if we can
// make the types of other paging data variable
data class BaseDetailsScreenState<T : EntityModel>(
    val primaryId: Int? = null,
    val primaryRes: Resource<T> = Resource.Loading,
    override val entityCountsData: StateFlow<EntityCountsData>,
    override val characterData: StateFlow<PagingData<Character>>,
    override val creatorsData: StateFlow<PagingData<Creator>>,
    override val eventsData: StateFlow<PagingData<Event>>,
    override val storiesData: StateFlow<PagingData<Story>>,
    override val seriesData: StateFlow<PagingData<Series>>,
    override val comicData: StateFlow<PagingData<Comic>>
) : PagingDataConsumerScreenState