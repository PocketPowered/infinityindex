package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.network.NetworkCreator
import com.wongislandd.infinityindex.viewmodels.slices.AllCreatorsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.CreatorsListScreenStateSlice

class AllCreatorsListViewModel(
    screenStateSlice: CreatorsListScreenStateSlice,
    sortSlice: SortSlice,
    searchSlice: SearchSlice,
    pagingSlice: AllCreatorsPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCreator, Creator>(
    screenStateSlice = screenStateSlice,
    sortSlice = sortSlice,
    searchSlice = searchSlice,
    pagingSlice = pagingSlice,
    uiEventBus = uiEventBus,
    backChannelEventBus = backChannelEventBus
)