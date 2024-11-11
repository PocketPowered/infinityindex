package com.wongislandd.infinityindex.comics.details.viewmodels

import androidx.lifecycle.ViewModel
import com.wongislandd.infinityindex.comics.details.models.ComicDetailsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComicDetailsViewModel(private val comicId: Int) : ViewModel() {
    private val _screenState: MutableStateFlow<ComicDetailsScreenState> =
        MutableStateFlow(ComicDetailsScreenState())

    val screenState: StateFlow<ComicDetailsScreenState> = _screenState

    init {

    }
}