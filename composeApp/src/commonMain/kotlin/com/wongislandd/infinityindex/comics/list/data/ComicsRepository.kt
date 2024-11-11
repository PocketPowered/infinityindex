package com.wongislandd.infinityindex.comics.list.data

import com.wongislandd.infinityindex.networking.MarvelDataWrapper
import com.wongislandd.infinityindex.comics.list.models.ComicsSortOption
import com.wongislandd.infinityindex.networking.NetworkMarvelDataWrapper
import com.wongislandd.infinityindex.networking.DataWrapperTransformer
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsRepository(
    private val dataWrapperTransformer: DataWrapperTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAllComics(
        start: Int,
        count: Int,
        searchParam: String?,
        sortOption: ComicsSortOption
    ): Resource<MarvelDataWrapper> {
        val response: Resource<NetworkMarvelDataWrapper> = get<NetworkMarvelDataWrapper>("public/comics") {
            parameter("offset", start)
            parameter("limit", count)
            searchParam?.also { searchParam ->
                parameter("titleStartsWith", searchParam)
            }
            parameter("orderBy", sortOption.sortKey)
        }
        return response.map { dataWrapperTransformer.transform(it) }
    }

    suspend fun getComic(
        comicId: Int
    ): Resource<MarvelDataWrapper> {
        val response: Resource<NetworkMarvelDataWrapper> = get<NetworkMarvelDataWrapper>("public/comics/$comicId")
        return response.map { dataWrapperTransformer.transform(it) }
    }
    )
}