package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.viewmodels.shared.EventsListScreenStateSlice

class RelatedEventsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedEventsSlice: RelatedEventsSlice,
    eventsListScreenStateSlice: EventsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkEvent, Event>(
    eventsListScreenStateSlice,
    null,
    null,
    relatedEventsSlice,
    uiEventBus,
    backChannelEventBus
)