package com.wongislandd.infinityindex.entities.comics.details.data

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient

class ComicsEntityRepository(
    detailComicDataWrapperTransformer: DetailedComicTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkComic, Comic>(
    detailComicDataWrapperTransformer,
    okHttpClient,
    EntityType.COMICS,
    NetworkComic.serializer()
)