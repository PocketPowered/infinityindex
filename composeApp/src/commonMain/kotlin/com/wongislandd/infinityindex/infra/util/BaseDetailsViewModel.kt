package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.CharactersResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesResolutionSlice

abstract class BaseDetailsViewModel<T : PillarModel>(
    entityType: EntityType,
    screenStateSlice: BaseScreenStateSlice<T>,
    comicResolutionSlice: ComicResolutionSlice,
    storiesResolutionSlice: StoriesResolutionSlice,
    eventsResolutionSlice: EventsResolutionSlice,
    creatorsResolutionSlice: CreatorsResolutionSlice,
    charactersResolutionSlice: CharactersResolutionSlice,
    seriesDetailsResolutionSlice: SeriesResolutionSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel<T>(
    entityType,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus
) {

    init {
        registerSlice(screenStateSlice)
        registerSlice(comicResolutionSlice)
        registerSlice(storiesResolutionSlice)
        registerSlice(eventsResolutionSlice)
        registerSlice(creatorsResolutionSlice)
        registerSlice(charactersResolutionSlice)
        registerSlice(seriesDetailsResolutionSlice)
    }
}