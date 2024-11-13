package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wongislandd.infinityindex.infra.paging.EntityPagingSource
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.entities.series.data.SeriesEntityRepository
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsSeriesSlice(
    private val seriesRepository: SeriesEntityRepository
) : ViewModelSlice() {

    private val _seriesPagingData: MutableStateFlow<PagingData<Series>> =
        MutableStateFlow(PagingData.empty())
    val seriesPagingData: StateFlow<PagingData<Series>> = _seriesPagingData

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
                EntityPagingSource(seriesRepository, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _seriesPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.SeriesResUpdate(it)
                )
            }
        }
    }

}