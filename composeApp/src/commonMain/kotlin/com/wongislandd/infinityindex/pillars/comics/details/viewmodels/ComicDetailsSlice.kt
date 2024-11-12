package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import com.wongislandd.infinityindex.pillars.comics.ComicsRepository
import com.wongislandd.infinityindex.pillars.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent
import kotlinx.coroutines.launch

class ComicDetailsSlice(
    private val comicsRepository: ComicsRepository
) : ViewModelSlice() {

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ComicDetailsUiEvent.PageInitialized -> {
                initialize(event.comicId)
            }
        }
    }

    private fun initialize(comicId: Int) {
        loadComicDetails(comicId)
    }

    private fun loadComicDetails(comicId: Int) {
        sliceScope.launch {
            val comicRes = comicsRepository.getComic(comicId)
            backChannelEvents.sendEvent(
                ComicDetailsBackChannelEvent.DetailedComicsResUpdate(
                    comicRes
                )
            )
        }
    }
}