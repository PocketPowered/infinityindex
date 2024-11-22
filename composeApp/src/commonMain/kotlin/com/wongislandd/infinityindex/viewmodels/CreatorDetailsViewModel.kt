package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleCreatorSlice

class CreatorDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Creator>,
    comicResolutionSlice: RelatedComicsSlice,
    storiesResolutionSlice: RelatedStoriesSlice,
    eventsResolutionSlice: RelatedEventsSlice,
    singleCreatorSlice: SingleCreatorSlice,
    charactersResolutionSlice: RelatedCharactersSlice,
    seriesResolutionSlice: RelatedSeriesSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Creator>(
    EntityType.CREATORS,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        comicResolutionSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        singleCreatorSlice,
        charactersResolutionSlice,
        seriesResolutionSlice
    )
)