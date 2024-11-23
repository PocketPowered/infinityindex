package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.slices.ComicSeriesSupplementaryEntityResolutionSlice
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCharactersPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCreatorsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedEventsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedSeriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleComicSlice

class ComicDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Comic>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>,
    singleComicSlice: SingleComicSlice,
    seriesSupplementaryEntityResolutionSlice: ComicSeriesSupplementaryEntityResolutionSlice,
    comicsResolutionSlice: RelatedComicsPagingSlice,
    storiesResolutionSlice: RelatedStoriesPagingSlice,
    eventsResolutionSlice: RelatedEventsPagingSlice,
    creatorsResolutionSlice: RelatedCreatorsPagingSlice,
    charactersResolutionSlice: RelatedCharactersPagingSlice,
    seriesResolutionSlice: RelatedSeriesPagingSlice,
) : BaseDetailsViewModel<Comic>(
    EntityType.COMICS,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        seriesSupplementaryEntityResolutionSlice,
        comicsResolutionSlice,
        singleComicSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        charactersResolutionSlice,
        seriesResolutionSlice
    )
)