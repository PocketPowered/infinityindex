package com.wongislandd.infinityindex.comics.data

import com.wongislandd.infinityindex.comics.models.ComicsSortOption
import com.wongislandd.infinityindex.comics.models.NetworkComic
import com.wongislandd.infinityindex.comics.viewmodels.SearchQuery
import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.PaginationContextWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

class ComicsPagingSource(
    private val comicsRepository: ComicsRepository,
    private val searchQuery: SearchQuery? = null,
    private val sortOption: ComicsSortOption = ComicsSortOption.NONE
) : KoinComponent, BasePagingSource<NetworkComic>() {

    private val _isFetchingFirstPage: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isFetchingFirstPage: StateFlow<Boolean> = _isFetchingFirstPage

    override suspend fun fetchData(
        start: Int,
        count: Int
    ): Resource<PaginationContextWrapper<NetworkComic>> {
        return comicsRepository.getAllComics(start, count, searchParam = searchQuery?.text, sortOption).also {
            _isFetchingFirstPage.value = false
        }.map { data ->
            PaginationContextWrapper(
                items = data.data.results,
                start = data.data.offset,
                count = data.data.count,
                total = data.data.total.toLong()
            )
        }
    }
}