package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.viewmodels.shared.SeriesListScreenStateSlice

class RelatedSeriesListViewModel(
    uiEventBus: EventBus<UiEvent>,
    relatedSeriesSlice: RelatedSeriesSlice,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    seriesListScreenStateSlice: SeriesListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkSeries, Series>(
    seriesListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedSeriesSlice,
    uiEventBus,
    backChannelEventBus
)