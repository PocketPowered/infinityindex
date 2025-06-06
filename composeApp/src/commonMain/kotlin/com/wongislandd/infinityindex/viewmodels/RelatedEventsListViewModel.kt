package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.viewmodels.slices.EventsListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedEventsPagingSlice

class RelatedEventsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedEventsPagingSlice: RelatedEventsPagingSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    eventsListScreenStateSlice: EventsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkEvent, Event>(
    eventsListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedEventsPagingSlice,
    uiEventBus,
    backChannelEventBus
)