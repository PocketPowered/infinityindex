package com.wongislandd.infinityindex.viewmodels.related

import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.models.NetworkEvent
import com.wongislandd.infinityindex.repositories.EventsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType

class RelatedEventsSlice(
    eventsRepository: EventsEntityRepository
) : BaseRelatedEntitiesSlice<NetworkEvent, Event>(
    eventsRepository,
    EntityType.EVENTS
)