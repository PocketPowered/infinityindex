package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.util.SliceableViewModel
import com.wongislandd.infinityindex.util.events.BackChannelEvent
import com.wongislandd.infinityindex.util.events.EventBus
import com.wongislandd.infinityindex.util.events.UiEvent

class ComicDetailsViewModel(
    val comicDetailsScreenStateSlice: ComicDetailsScreenStateSlice,
    comicDetailsSlice: ComicDetailsSlice,
    comicDetailsStoriesSlice: ComicDetailsStoriesSlice,
    comicDetailsEventsSlice: ComicDetailsEventsSlice,
    comicDetailsCreatorsSlice: ComicDetailsCreatorsSlice,
    comicDetailsCharactersSlice: ComicDetailsCharactersSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlice(comicDetailsScreenStateSlice)
        registerSlice(comicDetailsSlice)
        registerSlice(comicDetailsStoriesSlice)
        registerSlice(comicDetailsEventsSlice)
        registerSlice(comicDetailsCreatorsSlice)
        registerSlice(comicDetailsCharactersSlice)
    }
}