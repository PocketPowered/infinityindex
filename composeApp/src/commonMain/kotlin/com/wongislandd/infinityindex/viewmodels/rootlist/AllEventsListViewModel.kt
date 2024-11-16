package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.viewmodels.shared.EventsListScreenStateSlice

class AllEventsListViewModel(
    screenStateSlice: EventsListScreenStateSlice,
    sortSlice: SortSlice,
    searchSlice: SearchSlice,
    pagingSlice: AllEventsPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkEvent, Event>(
    screenStateSlice = screenStateSlice,
    sortSlice = sortSlice,
    searchSlice = searchSlice,
    pagingSlice = pagingSlice,
    uiEventBus = uiEventBus,
    backChannelEventBus = backChannelEventBus
)