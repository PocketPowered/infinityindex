package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.comics.details.models.Comic
import com.wongislandd.infinityindex.util.events.BackChannelEvent

sealed class ComicDetailsBackChannelEvent : BackChannelEvent {
    data class ComicsResUpdate(val update: Resource<Comic>) : BackChannelEvent
    data class CharacterResUpdate(val update: PagingData<Character>) : BackChannelEvent
}