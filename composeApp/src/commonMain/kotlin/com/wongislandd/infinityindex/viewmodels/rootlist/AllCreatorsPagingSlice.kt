package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.network.NetworkCreator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository

class AllCreatorsPagingSlice(
    repository: CreatorsEntityRepository
): BaseListPagingSlice<NetworkCreator, Creator>(
    repository,
    EntityType.CREATORS,
    PagedListUseCase.ALL_AVAILABLE
)