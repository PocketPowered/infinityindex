package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.events.data.ComicEventsEntityRepository
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.infra.util.EntityType

class ComicDetailsEventsSlice(
    comicEventsRepository: ComicEventsEntityRepository
) : BaseSlice<NetworkComicEvent, ComicEvent>(
    comicEventsRepository,
    EntityType.COMIC_EVENTS
)