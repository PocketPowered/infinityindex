package com.wongislandd.infinityindex.home

import com.wongislandd.infinityindex.entities.characters.CharactersListPagingSlice
import com.wongislandd.infinityindex.entities.comics.ComicsListPagingSlice
import com.wongislandd.infinityindex.entities.creators.CreatorsListPagingSlice
import com.wongislandd.infinityindex.entities.events.EventsListPagingSlice
import com.wongislandd.infinityindex.entities.series.SeriesListPagingSlice
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

class HomeViewModel(
    screenStateSlice: HomeScreenStateSlice,
    comicsListPagingSlice: ComicsListPagingSlice,
    creatorsListPagingSlice: CreatorsListPagingSlice,
    charactersListPagingSlice: CharactersListPagingSlice,
    seriesListPagingSlice: SeriesListPagingSlice,
    eventsListPagingSlice: EventsListPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        registerSlice(screenStateSlice)
        registerSlice(comicsListPagingSlice)
        registerSlice(creatorsListPagingSlice)
        registerSlice(charactersListPagingSlice)
        registerSlice(seriesListPagingSlice)
        registerSlice(eventsListPagingSlice)
    }
}