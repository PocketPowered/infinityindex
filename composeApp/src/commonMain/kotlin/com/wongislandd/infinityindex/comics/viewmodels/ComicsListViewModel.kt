package com.wongislandd.infinityindex.comics.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.comics.data.ComicsPagingSource
import com.wongislandd.infinityindex.comics.data.ComicsRepository
import com.wongislandd.infinityindex.comics.models.ComicsSortOption
import com.wongislandd.infinityindex.comics.models.NetworkComic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicsListViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {

    // combine these into one observable state?
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _sortOption: MutableStateFlow<ComicsSortOption> =
        MutableStateFlow(ComicsSortOption.NONE)
    val sortOption = _sortOption.asStateFlow()

    private val _comicsResponse: MutableStateFlow<PagingData<NetworkComic>> =
        MutableStateFlow(PagingData.empty())
    val comicsResponse = _comicsResponse.asStateFlow()

    private var currentPagingSource: ComicsPagingSource? = null
    private var currentRefreshWatcherJob: Job? = null

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    initialLoadSize = 60,
                    pageSize = 20,
                    enablePlaceholders = false,
                    prefetchDistance = 10
                )
            ) {
                val newPagingSource = ComicsPagingSource(
                    comicsRepository = comicsRepository,
                    sortOption = _sortOption.value
                ).also { currentPagingSource = it }
                // Watch for refreshing state
                currentRefreshWatcherJob?.cancel()
                currentRefreshWatcherJob = CoroutineScope(Dispatchers.Main).launch {
                    newPagingSource.isFetchingFirstPage.collectLatest {
                        _isLoading.value = it
                    }
                }
                newPagingSource
            }.flow.collectLatest {
                _comicsResponse.value = it
            }
        }
    }

    fun setSortOption(sortOption: ComicsSortOption) {
        _sortOption.value = sortOption
        currentPagingSource?.invalidate()
    }

}