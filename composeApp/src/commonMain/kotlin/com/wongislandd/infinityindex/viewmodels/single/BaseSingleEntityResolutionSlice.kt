package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource.Loading.onSuccess
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.launch

/**
 * Good for getting details about a single entity, then broadcasting the
 * need for related entities to be loaded.
 */
abstract class BaseSingleEntityResolutionSlice<NETWORK_TYPE, LOCAL_TYPE : EntityModel>(
    private val entityType: EntityType,
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
) : ViewModelSlice() {

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is DetailsUiEvent.PageInitialized -> {
                if (event.primaryEntityType == entityType) {
                    loadSingleEntity(event.primaryId)
                }
            }
        }
    }

    private fun loadSingleEntity(primaryResourceId: Int) {
        sliceScope.launch {
            val singleEntityRes = repository.get(primaryResourceId)
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.SingleDataResUpdate(
                    update = singleEntityRes,
                    type = entityType
                )
            )
            // Send out the signals to start paging for related entities, see [BaseRelatedEntitiesSlice]
            singleEntityRes.onSuccess { entity ->
                launch {
                    sendRelatedPagingRequestSignals(primaryResourceId, entity)
                }
            }
        }
    }

    private suspend fun sendRelatedPagingRequestSignals(
        primaryResourceId: Int,
        entity: LOCAL_TYPE
    ) {
        if (entity.relatedComicsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.COMICS
                )
            )
        }
        if (entity.relatedCharactersCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.CHARACTERS
                )
            )
        }
        if (entity.relatedCreatorsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.CREATORS
                )
            )
        }
        if (entity.relatedEventsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.EVENTS
                )
            )
        }
        if (entity.relatedSeriesCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.SERIES
                )
            )
        }
        if (entity.relatedStoriesCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    rootEntityId = primaryResourceId,
                    rootEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.STORIES
                )
            )
        }
    }
}