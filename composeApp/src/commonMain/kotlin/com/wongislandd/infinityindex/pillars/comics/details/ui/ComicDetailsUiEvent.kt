package com.wongislandd.infinityindex.pillars.comics.details.ui

import com.wongislandd.infinityindex.util.events.UiEvent

sealed class ComicDetailsUiEvent: UiEvent {
    data class PageInitialized(val comicId: Int): ComicDetailsUiEvent()
}