package com.wongislandd.infinityindex.entities.creators.data

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.entities.creators.transformers.CreatorTransformer
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.SupportedPillars
import io.ktor.client.HttpClient

class CreatorsEntityRepository(
    creatorTransformer: CreatorTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkCreator, Creator>(
    creatorTransformer,
    okHttpClient,
    SupportedPillars.CREATORS,
    NetworkCreator.serializer()
)