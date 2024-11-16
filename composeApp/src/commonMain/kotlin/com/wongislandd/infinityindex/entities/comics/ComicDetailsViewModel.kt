package com.wongislandd.infinityindex.entities.comics

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.related.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.related.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleComicSlice

class ComicDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Comic>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>,
    singleComicSlice: SingleComicSlice,
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
        singleComicSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        charactersResolutionSlice,
        seriesResolutionSlice
    )
)