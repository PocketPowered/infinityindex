package com.wongislandd.infinityindex.entities.comics.details.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.util.ClientError
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsEntityRepository(
    private val detailComicDataWrapperTransformer: DetailedComicTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient), BasicEntityRepository<Comic> {

    override suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?
    ): Resource<DataWrapper<Comic>> {
        val response: Resource<NetworkDataWrapper<NetworkComic>> =
            get("comics") {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("titleStartsWith", searchParam)
                }
                parameter("orderBy", sortKey)
            }
        return response.map { detailComicDataWrapperTransformer.transform(it) }
    }

    override suspend fun get(
        id: Int
    ): Resource<Comic> {
        val response: Resource<NetworkDataWrapper<NetworkComic>> =
            get("comics/$id")
        return response.map { detailComicDataWrapperTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    override suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Comic>> {
        // should never need to call this
        return Resource.Error(ClientError.UNEXPECTED_STATE)
    }
}