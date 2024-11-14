package com.wongislandd.infinityindex.entities.creators.data

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.entities.creators.transformers.CreatorTransformer
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class CreatorsEntityRepository(
    creatorTransformer: CreatorTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkCreator, Creator>(
    creatorTransformer,
    okHttpClient,
    EntityType.CREATORS,
    typeInfo<NetworkDataWrapper<NetworkCreator>>()
)