package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.infra.util.PillarModel
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

class ComicDetailsViewModel(
    comicDetailsScreenStateSlice: ComicDetailsScreenStateSlice,
    comicDetailsSlice: ComicDetailsResolutionSlice,
    comicDetailsStoriesSlice: ComicDetailsStoriesResolutionSlice,
    comicDetailsEventsSlice: ComicDetailsEventsResolutionSlice,
    comicDetailsCreatorsSlice: ComicDetailsCreatorsResolutionSlice,
    comicDetailsCharactersSlice: ComicDetailsCharactersResolutionSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel<Comic>(comicDetailsScreenStateSlice, uiEventBus, backChannelEventBus) {

    init {
        registerSlice(comicDetailsScreenStateSlice)
        registerSlice(comicDetailsSlice)
        registerSlice(comicDetailsStoriesSlice)
        registerSlice(comicDetailsEventsSlice)
        registerSlice(comicDetailsCreatorsSlice)
        registerSlice(comicDetailsCharactersSlice)
    }
}