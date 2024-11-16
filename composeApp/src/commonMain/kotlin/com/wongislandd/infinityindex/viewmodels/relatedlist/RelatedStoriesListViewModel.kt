package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.entities.stories.StoriesListScreenStateSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.models.network.NetworkStory

class RelatedStoriesListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedStoriesSlice: RelatedStoriesSlice,
    storiesListScreenStateSlice: StoriesListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkStory, Story>(
    storiesListScreenStateSlice,
    null,
    null,
    relatedStoriesSlice,
    uiEventBus,
    backChannelEventBus
)