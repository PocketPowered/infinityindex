package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.comics.details.models.Comic
import com.wongislandd.infinityindex.util.events.BackChannelEvent

sealed class ComicDetailsBackChannelEvent: BackChannelEvent {
    data class DetailedComicsResUpdate(val update: Resource<Comic>): BackChannelEvent
}