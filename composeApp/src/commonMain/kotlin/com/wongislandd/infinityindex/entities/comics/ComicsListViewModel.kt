package com.wongislandd.infinityindex.entities.comics

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice

class ComicsListViewModel(
    screenStateSlice: BaseListScreenStateSlice<Comic>,
    sortSlice: SortSlice,
    pagingSlice: ComicsListPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkComic, Comic>(
    entityType = EntityType.COMICS,
    screenStateSlice = screenStateSlice,
    sortSlice = sortSlice,
    pagingSlice = pagingSlice,
    uiEventBus = uiEventBus,
    backChannelEventBus = backChannelEventBus
)