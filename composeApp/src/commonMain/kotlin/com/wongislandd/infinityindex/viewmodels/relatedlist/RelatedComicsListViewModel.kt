package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.viewmodels.shared.ComicsListScreenStateSlice

class RelatedComicsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedComicsSlice: RelatedComicsSlice,
    comicsListScreenStateSlice: ComicsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkComic, Comic>(
    comicsListScreenStateSlice,
    null,
    null,
    relatedComicsSlice,
    uiEventBus,
    backChannelEventBus
)