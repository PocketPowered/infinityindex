package com.wongislandd.infinityindex.home

import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.viewmodels.slices.AllCharactersPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllComicsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllCreatorsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllEventsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllSeriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllStoriesPagingSlice
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

class BrowseViewModel(
    val screenStateSlice: BrowseScreenStateSlice,
    allComicsPagingSlice: AllComicsPagingSlice,
    allCreatorsPagingSlice: AllCreatorsPagingSlice,
    allCharactersPagingSlice: AllCharactersPagingSlice,
    allSeriesPagingSlice: AllSeriesPagingSlice,
    allStoriesPagingSlice: AllStoriesPagingSlice,
    allEventsPagingSlice: AllEventsPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        listOf(
            allComicsPagingSlice,
            allCreatorsPagingSlice,
            allCharactersPagingSlice,
            allSeriesPagingSlice,
            allStoriesPagingSlice,
            allEventsPagingSlice
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
        registerSlice(allComicsPagingSlice)
        registerSlice(allCreatorsPagingSlice)
        registerSlice(allCharactersPagingSlice)
        registerSlice(allSeriesPagingSlice)
        registerSlice(allStoriesPagingSlice)
        registerSlice(allEventsPagingSlice)
    }
}