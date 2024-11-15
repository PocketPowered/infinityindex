package com.wongislandd.infinityindex.entities.creators

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice

class CreatorsListViewModel(
    screenStateSlice: BaseListScreenStateSlice<Creator>,
    sortSlice: SortSlice,
    searchSlice: SearchSlice,
    pagingSlice: CreatorsListPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCreator, Creator>(
    entityType = EntityType.CREATORS,
    screenStateSlice = screenStateSlice,
    sortSlice = sortSlice,
    searchSlice = searchSlice,
    pagingSlice = pagingSlice,
    uiEventBus = uiEventBus,
    backChannelEventBus = backChannelEventBus
)