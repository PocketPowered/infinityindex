package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.viewmodels.ComicSeriesSupplementaryEntityResolutionSlice
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleComicSlice

class ComicDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Comic>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>,
    singleComicSlice: SingleComicSlice,
    seriesSupplementaryEntityResolutionSlice: ComicSeriesSupplementaryEntityResolutionSlice,
    comicsResolutionSlice: RelatedComicsSlice,
    storiesResolutionSlice: RelatedStoriesSlice,
    eventsResolutionSlice: RelatedEventsSlice,
    creatorsResolutionSlice: RelatedCreatorsSlice,
    charactersResolutionSlice: RelatedCharactersSlice,
    seriesResolutionSlice: RelatedSeriesSlice,
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