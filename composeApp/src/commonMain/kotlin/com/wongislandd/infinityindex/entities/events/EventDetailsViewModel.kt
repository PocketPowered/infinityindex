package com.wongislandd.infinityindex.entities.events

import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleEventSlice

class EventDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Event>,
    comicResolutionSlice: RelatedComicsSlice,
    storiesResolutionSlice: RelatedStoriesSlice,
    singleEventSlice: SingleEventSlice,
    creatorsResolutionSlice: RelatedCreatorsSlice,
    charactersResolutionSlice: RelatedCharactersSlice,
    seriesResolutionSlice: RelatedSeriesSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Event>(
    EntityType.EVENTS,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        comicResolutionSlice,
        storiesResolutionSlice,
        singleEventSlice,
        creatorsResolutionSlice,
        charactersResolutionSlice,
        seriesResolutionSlice
    )
)