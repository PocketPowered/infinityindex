package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.repositories.ComicEventsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseResolutionSlice

class EventsResolutionSlice(
    comicEventsRepository: ComicEventsEntityRepository
) : BaseResolutionSlice<NetworkComicEvent, ComicEvent>(
    comicEventsRepository,
    EntityType.COMIC_EVENTS
)