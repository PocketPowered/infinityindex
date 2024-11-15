package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
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

class SeriesDetailsViewModel(
    screenStateSlice: BaseScreenStateSlice<Series>,
    comicResolutionSlice: ComicResolutionSlice,
    storiesResolutionSlice: StoriesResolutionSlice,
    eventsResolutionSlice: EventsResolutionSlice,
    creatorsResolutionSlice: CreatorsResolutionSlice,
    charactersResolutionSlice: CharactersResolutionSlice,
    seriesResolutionSlice: SeriesResolutionSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Series>(
    EntityType.SERIES,
    screenStateSlice,
    comicResolutionSlice,
    storiesResolutionSlice,
    eventsResolutionSlice,
    creatorsResolutionSlice,
    charactersResolutionSlice,
    seriesResolutionSlice,
    uiEventBus, backChannelEventBus
)