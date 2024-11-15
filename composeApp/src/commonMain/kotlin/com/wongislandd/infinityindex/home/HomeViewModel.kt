package com.wongislandd.infinityindex.home

import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.entities.characters.CharactersListPagingSlice
import com.wongislandd.infinityindex.entities.comics.ComicsListPagingSlice
import com.wongislandd.infinityindex.entities.creators.CreatorsListPagingSlice
import com.wongislandd.infinityindex.entities.events.EventsListPagingSlice
import com.wongislandd.infinityindex.entities.series.SeriesListPagingSlice
import com.wongislandd.infinityindex.entities.stories.StoriesListPagingSlice
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

class HomeViewModel(
    val screenStateSlice: HomeScreenStateSlice,
    comicsListPagingSlice: ComicsListPagingSlice,
    creatorsListPagingSlice: CreatorsListPagingSlice,
    charactersListPagingSlice: CharactersListPagingSlice,
    seriesListPagingSlice: SeriesListPagingSlice,
    storiesListPagingSlice: StoriesListPagingSlice,
    eventsListPagingSlice: EventsListPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        listOf(
            comicsListPagingSlice,
            creatorsListPagingSlice,
            charactersListPagingSlice,
            seriesListPagingSlice,
            storiesListPagingSlice,
            eventsListPagingSlice
        ).forEach {
            apply {
                it.setPagingConfig(
                    PagingConfig(
                        initialLoadSize = 8,
                        pageSize = 3,
                        enablePlaceholders = false,
                        prefetchDistance = 2
                    )
                )
            }
        }
        registerSlice(screenStateSlice)
        registerSlice(comicsListPagingSlice)
        registerSlice(creatorsListPagingSlice)
        registerSlice(charactersListPagingSlice)
        registerSlice(seriesListPagingSlice)
        registerSlice(storiesListPagingSlice)
        registerSlice(eventsListPagingSlice)
    }
}