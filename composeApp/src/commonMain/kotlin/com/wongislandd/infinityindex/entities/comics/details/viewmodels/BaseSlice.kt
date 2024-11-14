package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.paging.RelatedEntityPagingSource
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

enum class BaseSliceType {
    PAGING,
    SINGLE
}

abstract class BaseSlice<NETWORK_TYPE, LOCAL_TYPE : Any>(
    private val repository: BaseRepository<NETWORK_TYPE, LOCAL_TYPE>,
    private val entity: EntityType,
    private val config: BaseSliceType = BaseSliceType.PAGING,
) : ViewModelSlice() {

    private val _pagingData: MutableStateFlow<PagingData<LOCAL_TYPE>> =
        MutableStateFlow(PagingData.empty())
    val pagingData: MutableStateFlow<PagingData<LOCAL_TYPE>> = _pagingData

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ComicDetailsUiEvent.PageInitialized -> {
                load(event.comicId)
            }
        }
    }

    private fun load(comicId: Int) {
        sliceScope.launch {
            when (config) {
                BaseSliceType.PAGING -> {
                    launchPagingFlow(comicId)
                }
                BaseSliceType.SINGLE -> {
                    launchSingleGetFlow(comicId)
                }
            }
        }
    }

    private suspend fun launchSingleGetFlow(comicId: Int) {
        val singleItemRes = repository.get(comicId)
        backChannelEvents.sendEvent(
            ComicDetailsBackChannelEvent.SingleDataResUpdate(
                update = singleItemRes,
                type = entity
            )
        )
    }

    private suspend fun launchPagingFlow(comicId: Int) {
        Pager(
            config = PagingConfig(
                initialLoadSize = 5,
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 5
            )
        ) {
            RelatedEntityPagingSource(repository, EntityType.COMICS, comicId)
        }.flow.cachedIn(sliceScope).collectLatest {
            _pagingData.value = it
            backChannelEvents.sendEvent(
                ComicDetailsBackChannelEvent.PagingDataResUpdate(it, entity)
            )
        }
    }
}