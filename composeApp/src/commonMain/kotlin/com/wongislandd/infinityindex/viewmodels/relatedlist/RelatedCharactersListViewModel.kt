package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.viewmodels.shared.CharactersListScreenStateSlice

class RelatedCharactersListViewModel(
    uiEventBus: EventBus<UiEvent>,
    screenStateSlice: CharactersListScreenStateSlice,
    relatedCharactersSlice: RelatedCharactersSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlice(screenStateSlice)
        registerSlice(relatedCharactersSlice)
    }
}