package com.wongislandd.infinityindex.infra

import androidx.paging.PagingData
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SortOption
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class ListBackChannelEvent : BackChannelEvent {

    data class PagingDataResUpdate<T : EntityModel>(
        val update: PagingData<T>,
        val entityType: EntityType
    ) : ListBackChannelEvent()

    data class EntityCountsUpdate(
        val totalCount: Long,
        val entityType: EntityType
    ) : ListBackChannelEvent()

    data class UpdateLoadingState(val isLoading: Boolean) : ListBackChannelEvent()

    data class UpdateSearchBoxVisibility(val isVisible: Boolean) : ListBackChannelEvent()

    data class UpdatePendingSearchQuery(val query: String) : ListBackChannelEvent()

    data class SubmitSearchQuery(val query: String) : ListBackChannelEvent()

    data class SubmitSortSelection(val sortOption: SortOption) : ListBackChannelEvent()
}