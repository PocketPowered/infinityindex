package com.wongislandd.infinityindex.repositories

import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.transformers.ComicTransformer
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class ComicsEntityRepository(
    detailComicDataWrapperTransformer: ComicTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkComic, Comic>(
    detailComicDataWrapperTransformer,
    okHttpClient,
    EntityType.COMICS,
    typeInfo<NetworkDataWrapper<NetworkComic>>()
)