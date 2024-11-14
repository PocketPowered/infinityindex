package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseResolutionSlice

class CreatorsResolutionSlice(
    creatorsRepository: CreatorsEntityRepository
) : BaseResolutionSlice<NetworkCreator, Creator>(
    creatorsRepository,
    EntityType.CREATORS
)