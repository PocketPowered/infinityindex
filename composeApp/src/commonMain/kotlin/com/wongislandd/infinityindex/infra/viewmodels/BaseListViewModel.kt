package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

abstract class BaseListViewModel<NETWORK_TYPE, T : EntityModel>(
    val entityType: EntityType,
    val screenStateSlice: BaseListScreenStateSlice<T>,
    sortSlice: SortSlice,
    pagingSlice: BaseListPagingSlice<NETWORK_TYPE, T>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        registerSlice(screenStateSlice)
        registerSlice(sortSlice)
        registerSlice(pagingSlice)
    }
}