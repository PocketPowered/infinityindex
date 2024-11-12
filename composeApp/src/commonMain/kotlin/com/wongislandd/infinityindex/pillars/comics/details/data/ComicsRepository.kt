package com.wongislandd.infinityindex.pillars.comics.details.data

import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.comics.details.models.DetailedComic
import com.wongislandd.infinityindex.pillars.comics.details.transformers.DetailedComicDataWrapperTransformer
import com.wongislandd.infinityindex.pillars.comics.list.models.BasicComicDataWrapper
import com.wongislandd.infinityindex.pillars.comics.list.models.ComicsSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.pillars.comics.list.transformers.BasicComicDataWrapperTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsRepository(
    private val basicComicDataWrapperTransformer: BasicComicDataWrapperTransformer,
    private val detailComicDataWrapperTransformer: DetailedComicDataWrapperTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAllComics(
        start: Int,
        count: Int,
        searchParam: String?,
        sortOption: ComicsSortOption
    ): Resource<BasicComicDataWrapper> {
        val response: Resource<NetworkComicDataWrapper> = get<NetworkComicDataWrapper>("public/comics") {
            parameter("offset", start)
            parameter("limit", count)
            searchParam?.also { searchParam ->
                parameter("titleStartsWith", searchParam)
            }
            parameter("orderBy", sortOption.sortKey)
        }
        return response.map { basicComicDataWrapperTransformer.transform(it) }
    }

    suspend fun getComic(
        comicId: Int
    ): Resource<DetailedComic> {
        val response: Resource<NetworkComicDataWrapper> = get<NetworkComicDataWrapper>("public/comics/$comicId")
        return response.map { detailComicDataWrapperTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }
}