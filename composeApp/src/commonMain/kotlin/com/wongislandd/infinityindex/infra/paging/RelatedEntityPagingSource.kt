package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource

class RelatedEntityPagingSource<NETWORK_MODEL, LOCAL_MODEL: Any>(
    private val primaryEntityRepository: BaseRepository<NETWORK_MODEL, LOCAL_MODEL>,
    private val relatedEntityType: EntityType,
    private val relatedEntityId: Int
) : BasePagingSource<LOCAL_MODEL>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<LOCAL_MODEL>> {
        return primaryEntityRepository.getPagedPrimaryEntityRelatedToOtherEntity(
            relatedEntityType,
            relatedEntityId,
            start,
            count
        )
    }
}