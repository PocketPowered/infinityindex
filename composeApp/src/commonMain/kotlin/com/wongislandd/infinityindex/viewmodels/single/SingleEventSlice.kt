package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.models.NetworkEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.EventsEntityRepository

class SingleEventSlice(
    eventsRepository: EventsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkEvent, Event>(
    EntityType.EVENTS,
    eventsRepository
)