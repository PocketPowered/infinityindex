package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import kotlinx.coroutines.flow.StateFlow

class HomeScreenState(
    override val characterData: StateFlow<PagingData<Character>>,
    override val creatorsData: StateFlow<PagingData<Creator>>,
    override val eventsData: StateFlow<PagingData<Event>>,
    override val storiesData: StateFlow<PagingData<Story>>,
    override val seriesData: StateFlow<PagingData<Series>>,
    override val comicData: StateFlow<PagingData<Comic>>,
    override val entityCountsData: StateFlow<EntityCountsData>,
    override val hasLoaded: StateFlow<Boolean>
) : PagingDataConsumerScreenState