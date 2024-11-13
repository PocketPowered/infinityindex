package com.wongislandd.infinityindex.pillars.comics.details.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.comics.details.models.Comic
import com.wongislandd.infinityindex.pillars.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComic
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsRepository(
    private val detailComicDataWrapperTransformer: DetailedComicTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAllComics(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String
    ): Resource<DataWrapper<Comic>> {
        val response: Resource<NetworkDataWrapper<NetworkComic>> =
            get<NetworkDataWrapper<NetworkComic>>("public/comics") {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("titleStartsWith", searchParam)
                }
                parameter("orderBy", sortKey)
            }
        return response.map { detailComicDataWrapperTransformer.transform(it) }
    }

    suspend fun getComic(
        comicId: Int
    ): Resource<Comic> {
        val response: Resource<NetworkDataWrapper<NetworkComic>> =
            get<NetworkDataWrapper<NetworkComic>>("public/comics/$comicId")
        return response.map { detailComicDataWrapperTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }
}