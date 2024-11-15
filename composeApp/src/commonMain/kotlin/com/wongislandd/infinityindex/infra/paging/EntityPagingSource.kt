package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SortOption

class EntityPagingSource<NETWORK_MODEL, LOCAL_MODEL: EntityModel>(
    private val repository: BaseRepository<NETWORK_MODEL, LOCAL_MODEL>,
    private val searchQuery: String?,
    private val sortOption: SortOption? = null
) : BasePagingSource<LOCAL_MODEL>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<LOCAL_MODEL>> {
        return repository.getAll(
            start,
            count,
            searchQuery,
            sortOption?.sortKey
        )
    }
}