package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice

class StoriesListViewModel(
    screenStateSlice: BaseListScreenStateSlice<Story>,
    sortSlice: SortSlice,
    pagingSlice: StoriesListPagingSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkStory, Story>(
    entityType = EntityType.STORIES,
    screenStateSlice = screenStateSlice,
    sortSlice = sortSlice,
    pagingSlice = pagingSlice,
    uiEventBus = uiEventBus,
    backChannelEventBus = backChannelEventBus
)