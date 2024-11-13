package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import com.wongislandd.infinityindex.entities.comics.list.models.SearchQuery
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SortOption

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