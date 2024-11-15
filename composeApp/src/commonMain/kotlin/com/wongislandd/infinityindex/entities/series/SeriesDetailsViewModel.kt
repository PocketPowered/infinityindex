package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.CharactersDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesDetailsResolutionSlice

class SeriesDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Series>,
    comicResolutionSlice: ComicDetailsResolutionSlice,
    storiesResolutionSlice: StoriesDetailsResolutionSlice,
    eventsResolutionSlice: EventsDetailsResolutionSlice,
    creatorsResolutionSlice: CreatorsDetailsResolutionSlice,
    charactersResolutionSlice: CharactersDetailsResolutionSlice,
    seriesResolutionSlice: SeriesDetailsResolutionSlice,
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