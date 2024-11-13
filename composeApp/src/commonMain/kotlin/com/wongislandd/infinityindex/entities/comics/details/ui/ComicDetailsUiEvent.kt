package com.wongislandd.infinityindex.entities.comics.details.ui

import com.wongislandd.infinityindex.infra.util.events.UiEvent

sealed class ComicDetailsUiEvent: UiEvent {
    data class PageInitialized(val comicId: Int): ComicDetailsUiEvent()
}