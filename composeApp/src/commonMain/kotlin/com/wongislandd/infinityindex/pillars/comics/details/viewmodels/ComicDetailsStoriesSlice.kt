package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.pillars.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.pillars.stories.data.StoriesPagingSource
import com.wongislandd.infinityindex.pillars.stories.data.StoriesRepository
import com.wongislandd.infinityindex.pillars.stories.models.Story
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsStoriesSlice(
    private val storiesRepository: StoriesRepository
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
                StoriesPagingSource(storiesRepository, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _storiesPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.StoriesResUpdate(it)
                )
            }
        }
    }

}