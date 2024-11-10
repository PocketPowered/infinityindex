package com.wongislandd.infinityindex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wongislandd.infinityindex.networking.ComicsRepository
import com.wongislandd.infinityindex.networking.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicsListViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {
    private val _screenState: MutableStateFlow<Resource<NetworkComicDataWrapper>> =
        MutableStateFlow(Resource.Loading)
    val screenState: StateFlow<Resource<NetworkComicDataWrapper>> = _screenState

    init {
        viewModelScope.launch {
            comicsRepository.getAllComics().collectLatest {
                _screenState.value = it
            }
        }
    }

}