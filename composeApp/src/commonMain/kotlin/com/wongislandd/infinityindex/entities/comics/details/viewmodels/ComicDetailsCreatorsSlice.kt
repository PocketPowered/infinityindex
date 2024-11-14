package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.creators.data.CreatorsEntityRepository
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.infra.util.EntityType

class ComicDetailsCreatorsSlice(
    creatorsRepository: CreatorsEntityRepository
) : BaseSlice<NetworkCreator, Creator>(
    creatorsRepository,
    EntityType.COMICS
)