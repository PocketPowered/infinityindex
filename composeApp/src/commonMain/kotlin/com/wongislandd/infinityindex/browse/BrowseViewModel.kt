package com.wongislandd.infinityindex.browse

import app.cash.paging.createPagingConfig
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.viewmodels.slices.AllCharactersPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllComicsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllCreatorsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllEventsPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllSeriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.AllStoriesPagingSlice

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
        val pagingConfig = createPagingConfig(
            pageSize = 3
        )
        listOf(
            allComicsPagingSlice,
            allCreatorsPagingSlice,
            allCharactersPagingSlice,
            allSeriesPagingSlice,
            allStoriesPagingSlice,
            allEventsPagingSlice
        ).forEach {
            apply {
                it.setPagingConfig(pagingConfig, 1)
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