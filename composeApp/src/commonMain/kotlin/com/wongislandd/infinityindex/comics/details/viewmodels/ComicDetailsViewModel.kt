package com.wongislandd.infinityindex.comics.details.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wongislandd.infinityindex.comics.details.models.ComicDetailsScreenState
import com.wongislandd.infinityindex.comics.ComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComicDetailsViewModel(
    private val comicsRepository: ComicsRepository
) : ViewModel() {

    private val _screenState: MutableStateFlow<ComicDetailsScreenState> =
        MutableStateFlow(ComicDetailsScreenState())

    val screenState: StateFlow<ComicDetailsScreenState> = _screenState

    fun initialize(comicId: Int) {
        if (_screenState.value.comicId == comicId) {
            return
        }
        _screenState.value = _screenState.value.copy(comicId = comicId)
        loadComicDetails(comicId)
    }

    private fun loadComicDetails(comicId: Int) {
        viewModelScope.launch {
            val comic = comicsRepository.getComic(comicId)
            _screenState.value = screenState.value.copy(detailedComicRes = comic)
        }
    }
}