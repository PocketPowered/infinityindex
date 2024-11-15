package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.viewmodels.CharactersDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesDetailsResolutionSlice

abstract class BaseDetailsViewModel<T : EntityModel>(
    entityType: EntityType,
    final override val screenStateSlice: BaseDetailsScreenStateSlice<T>,
    comicResolutionSlice: ComicDetailsResolutionSlice,
    storiesResolutionSlice: StoriesDetailsResolutionSlice,
    eventsResolutionSlice: EventsDetailsResolutionSlice,
    creatorsResolutionSlice: CreatorsDetailsResolutionSlice,
    charactersResolutionSlice: CharactersDetailsResolutionSlice,
    seriesDetailsResolutionSlice: SeriesDetailsResolutionSlice,
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