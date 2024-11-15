package com.wongislandd.infinityindex.entities.events

import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class EventsListScreenStateSlice: BaseListScreenStateSlice<Event>(
    EntityType.EVENTS
)