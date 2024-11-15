package com.wongislandd.infinityindex.infra

import androidx.paging.PagingData
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.SortOption
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class ListBackChannelEvent : BackChannelEvent {

    data class PagingDataResUpdate<T : EntityModel>(
        val update: PagingData<T>,
    ) : ListBackChannelEvent()

    data class PagingRefreshingUpdate(val refreshing: Boolean) : ListBackChannelEvent()

    data class UpdateSearchBoxVisibility(val isVisible: Boolean) : ListBackChannelEvent()

    data class UpdatePendingSearchQuery(val query: String) : ListBackChannelEvent()

    data class SubmitSearchQuery(val query: String) : ListBackChannelEvent()

    data class SubmitSortSelection(val sortOption: SortOption) : ListBackChannelEvent()
}