package com.wongislandd.infinityindex.pillars.comics.list.data

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.comics.details.data.ComicsRepository
import com.wongislandd.infinityindex.pillars.comics.details.models.Comic
import com.wongislandd.infinityindex.pillars.comics.list.models.ComicsSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.SearchQuery
import org.koin.core.component.KoinComponent

class ComicsListPagingSource(
    private val comicsRepository: ComicsRepository,
    private val searchQuery: SearchQuery? = null,
    private val sortOption: ComicsSortOption
) : KoinComponent, BasePagingSource<Comic>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Comic>> {
        return comicsRepository.getAll(
            start,
            count,
            searchParam = searchQuery?.text,
            sortOption.sortKey
        )
    }

}