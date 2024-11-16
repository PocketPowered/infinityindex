package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.viewmodels.shared.CreatorsListScreenStateSlice

class RelatedCreatorsListViewModel(
    uiEventBus: EventBus<UiEvent>,
    creatorsListScreenStateSlice: CreatorsListScreenStateSlice,
    relatedCreatorsSlice: RelatedCreatorsSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlice(creatorsListScreenStateSlice)
        registerSlice(relatedCreatorsSlice)
    }
}