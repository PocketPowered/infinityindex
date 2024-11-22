package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleSeriesSlice

class SeriesDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Series>,
    comicResolutionSlice: RelatedComicsSlice,
    storiesResolutionSlice: RelatedStoriesSlice,
    eventsResolutionSlice: RelatedEventsSlice,
    creatorsResolutionSlice: RelatedCreatorsSlice,
    charactersResolutionSlice: RelatedCharactersSlice,
    singleSeriesSlice: SingleSeriesSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Series>(
    EntityType.SERIES,
    screenStateSlice,

    uiEventBus,
    backChannelEventBus,
    listOf(
        comicResolutionSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        charactersResolutionSlice,
        singleSeriesSlice
    )
)