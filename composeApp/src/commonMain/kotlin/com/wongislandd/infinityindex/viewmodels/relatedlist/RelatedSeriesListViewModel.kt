package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.viewmodels.shared.SeriesListScreenStateSlice

class RelatedSeriesListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedSeriesSlice: RelatedSeriesSlice,
    seriesListScreenStateSlice: SeriesListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(uiEventBus, backChannelEventBus) {

    init {
        registerSlice(seriesListScreenStateSlice)
        registerSlice(relatedSeriesSlice)
    }
}