package com.wongislandd.infinityindex.comics.details.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wongislandd.infinityindex.comics.details.models.ComicDetailsScreenState
import com.wongislandd.infinityindex.comics.ComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComicDetailsViewModel(
    private val comicId: Int,
    private val comicsRepository: ComicsRepository
) : ViewModel() {
    private val _screenState: MutableStateFlow<ComicDetailsScreenState> =
        MutableStateFlow(ComicDetailsScreenState(comicId))

    val screenState: StateFlow<ComicDetailsScreenState> = _screenState

    init {
        viewModelScope.launch {
            // Fetch comic details
            val comic = comicsRepository.getComic(comicId)
        }
    }
}