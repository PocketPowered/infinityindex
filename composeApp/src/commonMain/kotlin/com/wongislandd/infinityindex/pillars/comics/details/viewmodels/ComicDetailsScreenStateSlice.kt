package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import com.wongislandd.infinityindex.pillars.comics.details.models.ComicDetailsScreenState
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComicDetailsScreenStateSlice: ViewModelSlice() {
    private val _screenState: MutableStateFlow<ComicDetailsScreenState> =
        MutableStateFlow(ComicDetailsScreenState())

    val screenState: StateFlow<ComicDetailsScreenState> = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ComicDetailsBackChannelEvent.DetailedComicsResUpdate -> {
                _screenState.value = _screenState.value.copy(comicRes = event.update)
            }
        }
    }
}