package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class EventsListScreenStateSlice(
    dataStoreRepository: DataStoreRepository
): BaseListScreenStateSlice<Event>(
    EntityType.EVENTS,
    dataStoreRepository
)