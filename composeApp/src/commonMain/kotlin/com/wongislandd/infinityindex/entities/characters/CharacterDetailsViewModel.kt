package com.wongislandd.infinityindex.entities.characters

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.CharactersDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesDetailsResolutionSlice

class CharacterDetailsViewModel(
    screenStateSlice: BaseDetailsScreenStateSlice<Character>,
    comicResolutionSlice: ComicDetailsResolutionSlice,
    storiesResolutionSlice: StoriesDetailsResolutionSlice,
    eventsResolutionSlice: EventsDetailsResolutionSlice,
    creatorsResolutionSlice: CreatorsDetailsResolutionSlice,
    charactersResolutionSlice: CharactersDetailsResolutionSlice,
    seriesDetailsResolutionSlice: SeriesDetailsResolutionSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseDetailsViewModel<Character>(
    EntityType.CHARACTERS,
    screenStateSlice,
    comicResolutionSlice,
    storiesResolutionSlice,
    eventsResolutionSlice,
    creatorsResolutionSlice,
    charactersResolutionSlice,
    seriesDetailsResolutionSlice,
    uiEventBus, backChannelEventBus
)