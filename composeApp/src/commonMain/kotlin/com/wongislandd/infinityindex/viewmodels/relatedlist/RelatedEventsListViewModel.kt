package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.viewmodels.shared.EventsListScreenStateSlice

class RelatedEventsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedEventsSlice: RelatedEventsSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    eventsListScreenStateSlice: EventsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkEvent, Event>(
    eventsListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedEventsSlice,
    uiEventBus,
    backChannelEventBus
)