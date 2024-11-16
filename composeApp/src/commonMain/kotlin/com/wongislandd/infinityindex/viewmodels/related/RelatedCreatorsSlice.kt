package com.wongislandd.infinityindex.viewmodels.related

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType

class RelatedCreatorsSlice(
    creatorsRepository: CreatorsEntityRepository
) : BaseRelatedEntitiesSlice<NetworkCreator, Creator>(
    creatorsRepository,
    EntityType.CREATORS
)