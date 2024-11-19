package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource.Loading.onSuccess
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.launch

abstract class BaseSupplementaryEntityResolutionSlice<LOCAL_TYPE : EntityModel>(
    private val repository: BaseRepository<*, LOCAL_TYPE>,
    private val entityType: EntityType
) : ViewModelSlice() {

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is DetailsBackChannelEvent.RequestForSingleRelatedDataUpdate -> {
                if (entityType == event.entityType) {
                    launchSingleGetResolution(event.entityId)
                }
            }
        }
    }

    private fun launchSingleGetResolution(id: Int) {
        sliceScope.launch {
            val response = repository.get(id)
            response.onSuccess { data ->
                launch {
                    backChannelEvents.sendEvent(
                        DetailsBackChannelEvent.SingleRelatedDataUpdate(
                            data,
                            entityType
                        )
                    )
                }
            }
        }
    }
}