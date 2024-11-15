package com.wongislandd.infinityindex.entities.events

import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.models.NetworkEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.repositories.EventsEntityRepository

class EventsListPagingSlice(repository: EventsEntityRepository) :
    BaseListPagingSlice<NetworkEvent, Event>(
        repository,
        EntityType.EVENTS
    )