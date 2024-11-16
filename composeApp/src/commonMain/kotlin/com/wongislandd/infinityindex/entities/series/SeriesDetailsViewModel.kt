package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleSeriesSlice

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