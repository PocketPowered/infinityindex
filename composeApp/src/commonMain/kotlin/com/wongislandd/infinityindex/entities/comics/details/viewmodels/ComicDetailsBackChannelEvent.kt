package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class ComicDetailsBackChannelEvent : BackChannelEvent {
    data class PagingDataResUpdate<T : Any>(val update: PagingData<T>, val type: EntityType) :
        BackChannelEvent

    data class SingleDataResUpdate<T : Any>(val update: Resource<T>, val type: EntityType) : BackChannelEvent

}