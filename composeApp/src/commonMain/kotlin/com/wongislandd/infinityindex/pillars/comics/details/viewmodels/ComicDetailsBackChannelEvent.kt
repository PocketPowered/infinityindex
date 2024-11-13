package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.comics.details.models.Comic
import com.wongislandd.infinityindex.pillars.creators.models.Creator
import com.wongislandd.infinityindex.pillars.events.models.ComicEvent
import com.wongislandd.infinityindex.pillars.series.models.Series
import com.wongislandd.infinityindex.pillars.stories.models.Story
import com.wongislandd.infinityindex.util.events.BackChannelEvent

sealed class ComicDetailsBackChannelEvent : BackChannelEvent {
    data class ComicsResUpdate(val update: Resource<Comic>) : BackChannelEvent
    data class CharacterResUpdate(val update: PagingData<Character>) : BackChannelEvent
    data class CreatorResUpdate(val update: PagingData<Creator>) : BackChannelEvent
    data class SeriesResUpdate(val update: PagingData<Series>) : BackChannelEvent
    data class EventsResUpdate(val update: PagingData<ComicEvent>) : BackChannelEvent
    data class StoriesResUpdate(val update: PagingData<Story>) : BackChannelEvent
}