package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

class ComicDetailsViewModel(
    comicDetailsScreenStateSlice: ComicDetailsScreenStateSlice,
    comicDetailsSlice: ComicResolutionSlice,
    comicDetailsStoriesSlice: StoriesResolutionSlice,
    comicDetailsEventsSlice: EventsResolutionSlice,
    comicDetailsCreatorsSlice: CreatorsResolutionSlice,
    comicDetailsCharactersSlice: CharactersResolutionSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel<Comic>(
    EntityType.COMICS,
    comicDetailsScreenStateSlice, uiEventBus, backChannelEventBus
) {

    init {
        registerSlice(comicDetailsScreenStateSlice)
        registerSlice(comicDetailsSlice)
        registerSlice(comicDetailsStoriesSlice)
        registerSlice(comicDetailsEventsSlice)
        registerSlice(comicDetailsCreatorsSlice)
        registerSlice(comicDetailsCharactersSlice)
    }
}