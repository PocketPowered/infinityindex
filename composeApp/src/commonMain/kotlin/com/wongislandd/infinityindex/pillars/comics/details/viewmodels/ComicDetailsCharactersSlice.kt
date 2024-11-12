package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import com.wongislandd.infinityindex.pillars.characters.data.CharactersRepository
import com.wongislandd.infinityindex.pillars.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent

class ComicDetailsCharactersSlice(
    private val charactersRepository: CharactersRepository
): ViewModelSlice() {
    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ComicDetailsUiEvent.PageInitialized -> {
                initialize(event.comicId)
            }
        }
    }

    private fun initialize(comicId: Int) {
        loadCharacterDetails(comicId)
    }

    private fun loadCharacterDetails(comicId: Int) {

    }

}