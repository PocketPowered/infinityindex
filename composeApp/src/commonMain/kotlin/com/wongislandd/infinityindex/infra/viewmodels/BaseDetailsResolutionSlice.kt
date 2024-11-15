package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.ListBackChannelEvent
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.paging.RelatedEntityPagingSource
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource.Loading.onSuccess
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseListResolutionSlice<NETWORK_TYPE, LOCAL_TYPE : EntityModel>(
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
) : ViewModelSlice() {

}

abstract class BaseDetailsResolutionSlice<NETWORK_TYPE, LOCAL_TYPE : EntityModel>(
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
    private val entityType: EntityType,
) : ViewModelSlice() {

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is DetailsUiEvent.PageInitialized -> {
                if (event.primaryEntityType == entityType) {
                    loadSingleEntity(event.comicId)
                }
            }
        }
    }

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is DetailsBackChannelEvent.RequestForPagination -> {
                if (event.relatedEntityTypeToPageFor == entityType) {
                    launchRelatedPagingFlow(event.primaryEntityId, event.primaryEntityType)
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
            // Send out the signals to start paging for related entities
            singleEntityRes.onSuccess { entity ->
                launch {
                    sendRelatedPagingRequestSignals(primaryResourceId, entity)
                }
            }
        }
    }

    private fun launchRelatedPagingFlow(primaryEntityId: Int, relatedEntityType: EntityType) {
        sliceScope.launch {
            Pager(
                config = PagingConfig(
                    initialLoadSize = 5,
                    pageSize = 5,
                    enablePlaceholders = false,
                    prefetchDistance = 2
                )
            ) {
                RelatedEntityPagingSource(repository, relatedEntityType, primaryEntityId)
            }.flow.cachedIn(sliceScope).collectLatest {
                backChannelEvents.sendEvent(
                    ListBackChannelEvent.PagingDataResUpdate(it, entityType)
                )
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
                    primaryEntityId = primaryResourceId,
                    primaryEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.COMICS
                )
            )
        }
        if (entity.relatedCharactersCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    primaryEntityId = primaryResourceId,
                    primaryEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.CHARACTERS
                )
            )
        }
        if (entity.relatedCreatorsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    primaryEntityId = primaryResourceId,
                    primaryEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.CREATORS
                )
            )
        }
        if (entity.relatedEventsCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    primaryEntityId = primaryResourceId,
                    primaryEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.EVENTS
                )
            )
        }
        if (entity.relatedSeriesCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    primaryEntityId = primaryResourceId,
                    primaryEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.SERIES
                )
            )
        }
        if (entity.relatedStoriesCount > 0) {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    primaryEntityId = primaryResourceId,
                    primaryEntityType = entityType,
                    relatedEntityTypeToPageFor = EntityType.STORIES
                )
            )
        }
    }
}