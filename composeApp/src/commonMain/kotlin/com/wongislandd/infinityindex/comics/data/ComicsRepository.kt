package com.wongislandd.infinityindex.comics.data

import com.wongislandd.infinityindex.comics.models.ComicDataWrapper
import com.wongislandd.infinityindex.comics.models.ComicsSortOption
import com.wongislandd.infinityindex.comics.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.comics.transformers.ComicDataWrapperTransformer
import com.wongislandd.infinityindex.comics.transformers.ComicTransformer
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsRepository(
    private val comicDataWrapperTransformer: ComicDataWrapperTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAllComics(
        start: Int,
        count: Int,
        searchParam: String?,
        sortOption: ComicsSortOption
    ): Resource<ComicDataWrapper> {
        val response: Resource<NetworkComicDataWrapper> = get<NetworkComicDataWrapper>("public/comics") {
            parameter("offset", start)
            parameter("limit", count)
            searchParam?.also { searchParam ->
                parameter("titleStartsWith", searchParam)
            }
            parameter("orderBy", sortOption.sortKey)
        }
        return response.map { comicDataWrapperTransformer.transform(it) }
    }
}