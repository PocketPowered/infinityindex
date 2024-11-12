package com.wongislandd.infinityindex.pillars.comics.list.data

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.PaginationContextWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.comics.details.data.ComicsRepository
import com.wongislandd.infinityindex.pillars.comics.list.models.BasicComic
import com.wongislandd.infinityindex.pillars.comics.list.models.ComicsSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.SearchQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

class ComicsListPagingSource(
    private val comicsRepository: ComicsRepository,
    private val searchQuery: SearchQuery? = null,
    private val sortOption: ComicsSortOption
) : KoinComponent, BasePagingSource<BasicComic>() {

    private val _isFetchingFirstPage: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isFetchingFirstPage: StateFlow<Boolean> = _isFetchingFirstPage

    override suspend fun fetchData(
        start: Int,
        count: Int
    ): Resource<PaginationContextWrapper<BasicComic>> {
        return comicsRepository.getAllComics(
            start,
            count,
            searchParam = searchQuery?.text,
            sortOption
        ).also {
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