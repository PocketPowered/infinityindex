package com.wongislandd.infinityindex.infra

import androidx.paging.PagingData
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SortOption
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class PagingBackChannelEvent : BackChannelEvent {

    data class PagingDataResUpdate<T : EntityModel>(
        val update: PagingData<T>,
        val entityType: EntityType
    ) : PagingBackChannelEvent()

    data class EntityCountsUpdate(
        val totalCount: Long,
        val entityType: EntityType
    ) : PagingBackChannelEvent()

    data class PagingResponseReceived(
        val response: Resource<Any>,
        val entityType: EntityType
    ): PagingBackChannelEvent()

    data class UpdateSearchBoxVisibility(val isVisible: Boolean) : PagingBackChannelEvent()

    data class UpdatePendingSearchQuery(val query: String) : PagingBackChannelEvent()

    data class SubmitSearchQuery(val query: String) : PagingBackChannelEvent()

    data class SubmitSortSelection(val sortOption: SortOption) : PagingBackChannelEvent()
}