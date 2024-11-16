package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.network.NetworkCreator
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedCreatorsSlice(
    creatorsRepository: CreatorsEntityRepository
) : BaseListPagingSlice<NetworkCreator, Creator>(
    creatorsRepository,
    EntityType.CREATORS,
    PagedListUseCase.RELATED_ENTITIES
)