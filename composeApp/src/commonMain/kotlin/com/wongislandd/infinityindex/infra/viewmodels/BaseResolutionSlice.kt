package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicDetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.paging.RelatedEntityPagingSource
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.PillarModel
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseResolutionSlice<NETWORK_TYPE, LOCAL_TYPE : PillarModel>(
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
    private val primaryEntityType: EntityType,
) : ViewModelSlice<LOCAL_TYPE>() {

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is DetailsUiEvent.PageInitialized -> {
                load(event.comicId, event.primaryEntityType)
            }
        }
    }

    private fun load(comicId: Int, relatedEntityType: EntityType) {
        val isPrimaryEntity = primaryEntityType == relatedEntityType
        sliceScope.launch {
            if (isPrimaryEntity) {
                launchGetPrimaryEntityDetailsFlow(comicId)
            } else {
                launchRelatedPagingFlow(comicId, relatedEntityType)
            }
        }
    }

    private suspend fun launchGetPrimaryEntityDetailsFlow(comicId: Int) {
        val singleItemRes = repository.get(comicId)
        backChannelEvents.sendEvent(
            ComicDetailsBackChannelEvent.SingleDataResUpdate(
                update = singleItemRes,
                type = primaryEntityType
            )
        )
    }

    private suspend fun launchRelatedPagingFlow(comicId: Int, relatedEntityType: EntityType) {
        Pager(
            config = PagingConfig(
                initialLoadSize = 5,
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 5
            )
        ) {
            RelatedEntityPagingSource(repository, relatedEntityType, comicId)
        }.flow.cachedIn(sliceScope).collectLatest {
            backChannelEvents.sendEvent(
                ComicDetailsBackChannelEvent.PagingDataResUpdate(it, primaryEntityType)
            )
        }
    }
}