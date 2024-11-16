package com.wongislandd.infinityindex.entities.creators

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleCreatorSlice

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