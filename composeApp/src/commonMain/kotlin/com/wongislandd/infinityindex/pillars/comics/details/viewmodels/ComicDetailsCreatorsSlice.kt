package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.pillars.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.pillars.creators.data.CreatorsPagingSource
import com.wongislandd.infinityindex.pillars.creators.data.CreatorsRepository
import com.wongislandd.infinityindex.pillars.creators.models.Creator
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsCreatorsSlice(
    private val creatorsRepository: CreatorsRepository
) : ViewModelSlice() {

    private val _creatorsPagingData: MutableStateFlow<PagingData<Creator>> =
        MutableStateFlow(PagingData.empty())
    val creatorsPagingData: StateFlow<PagingData<Creator>> = _creatorsPagingData

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
                CreatorsPagingSource(creatorsRepository, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _creatorsPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.CreatorResUpdate(it)
                )
            }
        }
    }

}