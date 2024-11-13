package com.wongislandd.infinityindex.networking.util

import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import com.wongislandd.infinityindex.entities.comics.list.models.SearchQuery

class EntityPagingSource<T : Any>(
    private val repository: BasicEntityRepository<T>,
    private val comicId: Int? = null,
    private val query: SearchQuery? = null,
    private val sortOption: SortOption? = null
) : BasePagingSource<T>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<T>> {
        return comicId?.let {
            repository.getPagedEntityFromComic(
                it,
                start,
                count
            )
        } ?: repository.getAll(
            start,
            count,
            query?.text,
            sortOption?.sortKey
        )
    }
}