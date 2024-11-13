package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.infra.paging.EntityPagingSource
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.entities.events.data.ComicEventsEntityRepository
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsEventsSlice(
    private val comicEventsRepository: ComicEventsEntityRepository
) : ViewModelSlice() {

    private val _eventsPagingData: MutableStateFlow<PagingData<ComicEvent>> =
        MutableStateFlow(PagingData.empty())
    val eventsPagingData: StateFlow<PagingData<ComicEvent>> = _eventsPagingData

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ComicDetailsUiEvent.PageInitialized -> {
                initialize(event.comicId)
            }
        }
    }

    private fun initialize(comicId: Int) {
        load(comicId)
    }

    private fun load(comicId: Int) {
        sliceScope.launch {
            Pager(
                config = PagingConfig(
                    initialLoadSize = 5,
                    pageSize = 5,
                    enablePlaceholders = false,
                    prefetchDistance = 5
                )
            ) {
                EntityPagingSource(comicEventsRepository, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _eventsPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.EventsResUpdate(it)
                )
            }
        }
    }

}