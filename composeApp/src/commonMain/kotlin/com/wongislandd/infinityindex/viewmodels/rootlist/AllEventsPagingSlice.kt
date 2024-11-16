package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.EventsEntityRepository

class AllEventsPagingSlice(repository: EventsEntityRepository) :
    BaseListPagingSlice<NetworkEvent, Event>(
        repository,
        EntityType.EVENTS,
        PagedListUseCase.ALL_AVAILABLE
)