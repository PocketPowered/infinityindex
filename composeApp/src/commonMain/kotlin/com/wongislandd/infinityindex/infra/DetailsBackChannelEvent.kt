package com.wongislandd.infinityindex.infra

import androidx.paging.PagingData
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class DetailsBackChannelEvent : BackChannelEvent {
    data class PagingDataResUpdate<T : EntityModel>(
        val update: PagingData<T>,
        val type: EntityType
    ) :
        BackChannelEvent

    data class SingleDataResUpdate<T : EntityModel>(val update: Resource<T>, val type: EntityType) :
        BackChannelEvent

}