package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.models.local.Story
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
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleStorySlice

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