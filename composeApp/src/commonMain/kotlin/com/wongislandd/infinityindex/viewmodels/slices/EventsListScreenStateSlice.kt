package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.models.local.Event

class EventsListScreenStateSlice: BaseListScreenStateSlice<Event>(
    EntityType.EVENTS
)