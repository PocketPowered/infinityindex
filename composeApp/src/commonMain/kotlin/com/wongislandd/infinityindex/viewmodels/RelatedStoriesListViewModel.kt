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
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesSlice

class RelatedStoriesListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedStoriesSlice: RelatedStoriesSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    storiesListScreenStateSlice: StoriesListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkStory, Story>(
    storiesListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedStoriesSlice,
    uiEventBus,
    backChannelEventBus
)