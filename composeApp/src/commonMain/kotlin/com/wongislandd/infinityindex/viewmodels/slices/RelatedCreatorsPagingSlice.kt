package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.network.NetworkCreator
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedCreatorsPagingSlice(
    creatorsRepository: CreatorsEntityRepository
) : BaseListPagingSlice<NetworkCreator, Creator>(
    creatorsRepository,
    EntityType.CREATORS,
    PagedListUseCase.RELATED_ENTITIES
)