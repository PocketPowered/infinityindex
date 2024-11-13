package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.networking.util.EntityPagingSource
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.entities.stories.data.StoriesEntityRepository
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsStoriesSlice(
    private val storiesRepository: StoriesEntityRepository
) : ViewModelSlice() {

    private val _storiesPagingData: MutableStateFlow<PagingData<Story>> =
        MutableStateFlow(PagingData.empty())
    val storiesPagingData: StateFlow<PagingData<Story>> = _storiesPagingData

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
                EntityPagingSource(storiesRepository, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _storiesPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.StoriesResUpdate(it)
                )
            }
        }
    }

}