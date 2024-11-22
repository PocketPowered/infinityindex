package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.EventsEntityRepository

class SingleEventSlice(
    eventsRepository: EventsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkEvent, Event>(
    EntityType.EVENTS,
    eventsRepository
)