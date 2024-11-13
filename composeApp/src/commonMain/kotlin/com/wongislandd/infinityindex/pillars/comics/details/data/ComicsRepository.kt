package com.wongislandd.infinityindex.pillars.comics.details.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.comics.details.models.DetailedComic
import com.wongislandd.infinityindex.pillars.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.pillars.comics.list.models.BasicComic
import com.wongislandd.infinityindex.pillars.comics.list.models.ComicsSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.pillars.comics.list.transformers.BasicComicTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsRepository(
    private val basicComicTransformer: BasicComicTransformer,
    private val detailComicDataWrapperTransformer: DetailedComicTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAllComics(
        start: Int,
        count: Int,
        searchParam: String?,
        sortOption: ComicsSortOption
    ): Resource<DataWrapper<BasicComic>> {
        val response: Resource<NetworkDataWrapper<NetworkComic>> =
            get<NetworkDataWrapper<NetworkComic>>("public/comics") {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("titleStartsWith", searchParam)
                }
                parameter("orderBy", sortOption.sortKey)
            }
        return response.map { basicComicTransformer.transform(it) }
    }

    suspend fun getComic(
        comicId: Int
    ): Resource<DetailedComic> {
        val response: Resource<NetworkDataWrapper<NetworkComic>> =
            get<NetworkDataWrapper<NetworkComic>>("public/comics/$comicId")
        return response.map { detailComicDataWrapperTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }
}