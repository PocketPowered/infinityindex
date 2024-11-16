package com.wongislandd.infinityindex.infra

import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent

sealed class DetailsBackChannelEvent : BackChannelEvent {

    data class SingleDataResUpdate<T : EntityModel>(val update: Resource<T>, val type: EntityType) :
        BackChannelEvent

    data class RequestForPagination(val rootEntityId: Int,
                                    val rootEntityType: EntityType,
                                    val relatedEntityTypeToPageFor: EntityType) : BackChannelEvent
}