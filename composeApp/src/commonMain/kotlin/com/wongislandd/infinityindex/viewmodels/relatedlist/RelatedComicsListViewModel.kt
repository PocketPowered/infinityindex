package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.viewmodels.shared.ComicsListScreenStateSlice

class RelatedComicsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedComicsSlice: RelatedComicsSlice,
    comicsListScreenStateSlice: ComicsListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlice(comicsListScreenStateSlice)
        registerSlice(relatedComicsSlice)
    }
}