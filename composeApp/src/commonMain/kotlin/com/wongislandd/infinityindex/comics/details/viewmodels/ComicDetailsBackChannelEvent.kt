package com.wongislandd.infinityindex.comics.details.viewmodels

import com.wongislandd.infinityindex.comics.details.models.DetailedComic
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.util.events.BackChannelEvent

sealed class ComicDetailsBackChannelEvent: BackChannelEvent {
    data class DetailedComicsResUpdate(val update: Resource<DetailedComic>): BackChannelEvent
}