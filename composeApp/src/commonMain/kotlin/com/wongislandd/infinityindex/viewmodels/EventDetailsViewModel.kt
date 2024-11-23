package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCharactersPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCreatorsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedSeriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleEventSlice

class EventDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Event>,
    comicResolutionSlice: RelatedComicsPagingSlice,
    storiesResolutionSlice: RelatedStoriesPagingSlice,
    singleEventSlice: SingleEventSlice,
    creatorsResolutionSlice: RelatedCreatorsPagingSlice,
    charactersResolutionSlice: RelatedCharactersPagingSlice,
    seriesResolutionSlice: RelatedSeriesPagingSlice,
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