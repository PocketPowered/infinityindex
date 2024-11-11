package com.wongislandd.infinityindex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.comics.ComicsPagingSource
import com.wongislandd.infinityindex.comics.ComicsRepository
import com.wongislandd.infinityindex.comics.NetworkComic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicsListViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {
    private val _comicsResponse: MutableStateFlow<PagingData<NetworkComic>> =
        MutableStateFlow(PagingData.empty())
    val comicsResponse = _comicsResponse.asStateFlow()

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    prefetchDistance = 10
                )
            ) {
                ComicsPagingSource(comicsRepository = comicsRepository)
            }.flow.collectLatest {
                _comicsResponse.value = it
            }
        }
    }

}