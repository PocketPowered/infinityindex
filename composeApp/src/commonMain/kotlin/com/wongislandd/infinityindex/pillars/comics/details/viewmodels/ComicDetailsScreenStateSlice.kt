package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.comics.details.models.ComicDetailsScreenState
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComicDetailsScreenStateSlice : ViewModelSlice() {

    private val _characterPagingData: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(PagingData.empty())

    private val _screenState: MutableStateFlow<ComicDetailsScreenState> =
        MutableStateFlow(ComicDetailsScreenState(
            characterData = _characterPagingData
        ))

    val screenState: StateFlow<ComicDetailsScreenState> = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ComicDetailsBackChannelEvent.ComicsResUpdate -> {
                _screenState.value = _screenState.value.copy(comicRes = event.update)
            }

            is ComicDetailsBackChannelEvent.CharacterResUpdate -> {
                _characterPagingData.value = event.update
            }
        }
    }
}