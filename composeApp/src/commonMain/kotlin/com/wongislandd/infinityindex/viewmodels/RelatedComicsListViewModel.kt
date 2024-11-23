package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.ComicsListScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsPagingSlice

class RelatedComicsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedComicsPagingSlice: RelatedComicsPagingSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    comicsListScreenStateSlice: ComicsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkComic, Comic>(
    comicsListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedComicsPagingSlice,
    uiEventBus,
    backChannelEventBus
)