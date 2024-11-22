package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.models.local.Story
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
import com.wongislandd.infinityindex.viewmodels.slices.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleStorySlice

class StoryDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Story>,
    comicResolutionSlice: RelatedComicsSlice,
    singleStorySlice: SingleStorySlice,
    eventsResolutionSlice: RelatedEventsSlice,
    creatorsResolutionSlice: RelatedCreatorsSlice,
    charactersResolutionSlice: RelatedCharactersSlice,
    seriesResolutionSlice: RelatedSeriesSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Story>(
    EntityType.STORIES,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        comicResolutionSlice,
        singleStorySlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        charactersResolutionSlice,
        seriesResolutionSlice
    )
)