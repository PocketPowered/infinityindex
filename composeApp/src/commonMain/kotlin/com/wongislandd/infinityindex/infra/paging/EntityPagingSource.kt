package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource

class EntityPagingSource<NETWORK_MODEL, LOCAL_MODEL: EntityModel>(
    private val repository: BaseRepository<NETWORK_MODEL, LOCAL_MODEL>,
    private val searchQuery: String?,
    private val sortKey: String? = null,
    private val digitalAvailabilityFilterEnabled: Boolean = false,
    private val isVariantsEnabled: Boolean = false,
) : BasePagingSource<LOCAL_MODEL>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<LOCAL_MODEL>> {
        return repository.getAll(
            start,
            count,
            searchQuery,
            sortKey,
            digitalAvailabilityFilterEnabled,
            isVariantsEnabled
        )
    }
}