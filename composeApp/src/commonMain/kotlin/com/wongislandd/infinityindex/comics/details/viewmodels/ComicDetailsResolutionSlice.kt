package com.wongislandd.infinityindex.comics.details.viewmodels

import com.wongislandd.infinityindex.comics.ComicsRepository
import com.wongislandd.infinityindex.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent
import kotlinx.coroutines.launch

class ComicDetailsResolutionSlice(
    private val comicsRepository: ComicsRepository
) : ViewModelSlice() {

    private var comicId: Int? = null

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ComicDetailsUiEvent.PageInitialized -> {
                initialize(event.comicId)
            }
        }
    }

    private fun initialize(comicId: Int) {
        if (this.comicId == comicId) {
            return
        }
        this.comicId = comicId
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