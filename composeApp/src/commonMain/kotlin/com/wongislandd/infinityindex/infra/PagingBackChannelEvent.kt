package com.wongislandd.infinityindex.infra

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SortOption
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class PagingBackChannelEvent : BackChannelEvent {

    data class PagingDataResUpdate(
        val update: PagingData<EntityModel>,
        val entityType: EntityType,
        val titleOfResults: String? = null
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

    data class SubmitDigitalAvailabilityFilterChange(val filterOn: Boolean) : PagingBackChannelEvent()

    data class VariantsFilterChange(val allowVariants: Boolean) : PagingBackChannelEvent()

    data class SubmitSortSelection(val sortOption: SortOption) : PagingBackChannelEvent()
}