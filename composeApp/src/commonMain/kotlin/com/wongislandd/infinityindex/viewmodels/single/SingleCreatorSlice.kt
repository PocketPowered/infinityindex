package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository

class SingleCreatorSlice(
    creatorRepository: CreatorsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkCreator, Creator>(
    EntityType.CREATORS,
    creatorRepository
)