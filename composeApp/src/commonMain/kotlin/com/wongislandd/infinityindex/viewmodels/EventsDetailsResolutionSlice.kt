package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.models.NetworkEvent
import com.wongislandd.infinityindex.repositories.EventsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsResolutionSlice

class EventsDetailsResolutionSlice(
    comicEventsRepository: EventsEntityRepository
) : BaseDetailsResolutionSlice<NetworkEvent, Event>(
    comicEventsRepository,
    EntityType.EVENTS
)