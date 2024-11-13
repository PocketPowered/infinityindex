package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class ComicDetailsBackChannelEvent : BackChannelEvent {
    data class ComicsResUpdate(val update: Resource<Comic>) : BackChannelEvent
    data class CharacterResUpdate(val update: PagingData<Character>) : BackChannelEvent
    data class CreatorResUpdate(val update: PagingData<Creator>) : BackChannelEvent
    data class SeriesResUpdate(val update: PagingData<Series>) : BackChannelEvent
    data class EventsResUpdate(val update: PagingData<ComicEvent>) : BackChannelEvent
    data class StoriesResUpdate(val update: PagingData<Story>) : BackChannelEvent
}