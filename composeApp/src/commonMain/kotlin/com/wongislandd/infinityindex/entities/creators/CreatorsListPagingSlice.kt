package com.wongislandd.infinityindex.entities.creators

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository

class CreatorsListPagingSlice(
    private val repository: CreatorsEntityRepository
): BaseListPagingSlice<NetworkCreator, Creator>(
    repository,
    EntityType.CREATORS
)