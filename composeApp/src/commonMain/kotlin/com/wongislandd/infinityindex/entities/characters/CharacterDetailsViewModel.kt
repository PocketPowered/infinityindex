package com.wongislandd.infinityindex.entities.characters

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleCharacterSlice

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