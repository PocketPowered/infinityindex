package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.repositories.EventsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedEventsSlice(
    eventsRepository: EventsEntityRepository
) : BaseListPagingSlice<NetworkEvent, Event>(
    eventsRepository,
    EntityType.EVENTS,
    PagedListUseCase.RELATED_ENTITIES
)