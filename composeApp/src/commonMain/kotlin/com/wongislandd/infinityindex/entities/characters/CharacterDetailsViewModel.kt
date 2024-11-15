package com.wongislandd.infinityindex.entities.characters

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.infra.util.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.CharactersResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesResolutionSlice

class CharacterDetailsViewModel(
    screenStateSlice: BaseScreenStateSlice<Character>,
    comicResolutionSlice: ComicResolutionSlice,
    storiesResolutionSlice: StoriesResolutionSlice,
    eventsResolutionSlice: EventsResolutionSlice,
    creatorsResolutionSlice: CreatorsResolutionSlice,
    charactersResolutionSlice: CharactersResolutionSlice,
    seriesDetailsResolutionSlice: SeriesResolutionSlice,
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