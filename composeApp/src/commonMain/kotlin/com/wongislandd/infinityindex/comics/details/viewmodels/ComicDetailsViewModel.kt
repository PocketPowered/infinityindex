package com.wongislandd.infinityindex.comics.details.viewmodels

import com.wongislandd.infinityindex.util.SliceableViewModel
import com.wongislandd.infinityindex.util.events.BackChannelEvent
import com.wongislandd.infinityindex.util.events.EventBus
import com.wongislandd.infinityindex.util.events.UiEvent

class ComicDetailsViewModel(
    val comicDetailsScreenStateSlice: ComicDetailsScreenStateSlice,
    comicDetailsResolutionSlice: ComicDetailsResolutionSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlice(comicDetailsScreenStateSlice)
        registerSlice(comicDetailsResolutionSlice)
    }
}