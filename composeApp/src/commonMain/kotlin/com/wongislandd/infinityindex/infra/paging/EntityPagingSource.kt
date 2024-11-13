package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.entities.comics.list.models.SearchQuery
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SortOption

class EntityPagingSource<NETWORK_MODEL, LOCAL_MODEL: Any>(
    private val repository: BaseRepository<NETWORK_MODEL, LOCAL_MODEL>,
    private val comicId: Int? = null,
    private val query: SearchQuery? = null,
    private val sortOption: SortOption? = null
) : BasePagingSource<LOCAL_MODEL>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<LOCAL_MODEL>> {
        return comicId?.let {
            repository.getPagedEntityFromComic(
                it,
                start,
                count
            )
        } ?: repository.getAll<NETWORK_MODEL>(
            start,
            count,
            query?.text,
            sortOption?.sortKey
        )
    }
}