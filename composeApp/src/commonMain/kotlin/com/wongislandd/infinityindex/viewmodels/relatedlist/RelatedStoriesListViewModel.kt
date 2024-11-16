package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.entities.stories.StoriesListScreenStateSlice
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

class RelatedStoriesListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedStoriesSlice: RelatedStoriesSlice,
    storiesListScreenStateSlice: StoriesListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlice(storiesListScreenStateSlice)
        registerSlice(relatedStoriesSlice)
    }
}