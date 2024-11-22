package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleCharacterSlice

class CharacterDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Character>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>,
    singleCharacterSlice: SingleCharacterSlice,
    comicResolutionSlice: RelatedComicsSlice,
    storiesResolutionSlice: RelatedStoriesSlice,
    eventsResolutionSlice: RelatedEventsSlice,
    creatorsResolutionSlice: RelatedCreatorsSlice,
    seriesDetailsResolutionSlice: RelatedSeriesSlice,
) : BaseDetailsViewModel<Character>(
    EntityType.CHARACTERS,
    screenStateSlice,
    uiEventBus,
    backChannelEventBus,
    listOf(
        singleCharacterSlice,
        comicResolutionSlice,
        storiesResolutionSlice,
        eventsResolutionSlice,
        creatorsResolutionSlice,
        seriesDetailsResolutionSlice
    )
)