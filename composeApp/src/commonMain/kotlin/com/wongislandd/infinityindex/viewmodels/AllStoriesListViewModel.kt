package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.viewmodels.slices.StoriesListScreenStateSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.models.network.NetworkStory
import com.wongislandd.infinityindex.viewmodels.slices.AllStoriesPagingSlice

class AllStoriesListViewModel(
    screenStateSlice: StoriesListScreenStateSlice,
    sortSlice: SortSlice,
    searchSlice: SearchSlice,
    pagingSlice: AllStoriesPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkStory, Story>(
    screenStateSlice = screenStateSlice,
    sortSlice = sortSlice,
    searchSlice = searchSlice,
    pagingSlice = pagingSlice,
    uiEventBus = uiEventBus,
    backChannelEventBus = backChannelEventBus
)