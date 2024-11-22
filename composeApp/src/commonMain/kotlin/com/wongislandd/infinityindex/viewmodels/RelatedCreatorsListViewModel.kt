package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.network.NetworkCreator
import com.wongislandd.infinityindex.viewmodels.slices.CreatorsListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCreatorsSlice

class RelatedCreatorsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedCreatorsSlice: RelatedCreatorsSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    creatorsListScreenStateSlice: CreatorsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCreator, Creator>(
    creatorsListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedCreatorsSlice,
    uiEventBus,
    backChannelEventBus
)