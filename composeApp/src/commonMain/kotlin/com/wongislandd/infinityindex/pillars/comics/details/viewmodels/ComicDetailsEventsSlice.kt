package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.pillars.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.pillars.events.data.EventsPagingSource
import com.wongislandd.infinityindex.pillars.events.data.EventsRepository
import com.wongislandd.infinityindex.pillars.events.models.ComicEvent
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsEventsSlice(
    private val eventsRepository: EventsRepository
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
                EventsPagingSource(eventsRepository, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _eventsPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.EventsResUpdate(it)
                )
            }
        }
    }

}