package com.wongislandd.infinityindex.comics

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.PaginationItems
import com.wongislandd.infinityindex.networking.util.Resource
import org.koin.core.component.KoinComponent

class ComicsPagingSource(
    private val sortOption: ComicsSortOption? = null,
    private val comicsRepository: ComicsRepository
) : KoinComponent, BasePagingSource<NetworkComic>() {

    override suspend fun fetchData(start: Int, count: Int): Resource<PaginationItems<NetworkComic>> {
        return comicsRepository.getAllComics(start, count, sortOption).map { data ->
            PaginationItems(
                items = data.data.results,
                start = data.data.offset,
                count = data.data.count,
                total = data.data.total.toLong()
            )
        }
    }
}