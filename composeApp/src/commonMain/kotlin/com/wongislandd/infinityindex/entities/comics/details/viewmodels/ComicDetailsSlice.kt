package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.comics.details.data.ComicsEntityRepository
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.launch

class ComicDetailsSlice(
    private val comicsRepository: ComicsEntityRepository
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
            val comicRes = comicsRepository.get(comicId)
            backChannelEvents.sendEvent(
                ComicDetailsBackChannelEvent.ComicsResUpdate(
                    comicRes
                )
            )
        }
    }
}