package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.network.NetworkCreator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository

class SingleCreatorSlice(
    creatorRepository: CreatorsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkCreator, Creator>(
    EntityType.CREATORS,
    creatorRepository
)