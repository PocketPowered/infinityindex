package com.wongislandd.infinityindex.pillars.comics.details.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.pillars.characters.data.CharactersPagingSource
import com.wongislandd.infinityindex.pillars.characters.data.CharactersRepository
import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsCharactersSlice(
    private val charactersRepository: CharactersRepository
) : ViewModelSlice() {

    private val _characterPagingData: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(PagingData.empty())
    val characterPagingData: StateFlow<PagingData<Character>> = _characterPagingData

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
                CharactersPagingSource(charactersRepository, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _characterPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.CharacterResUpdate(it)
                )
            }
        }
    }

}