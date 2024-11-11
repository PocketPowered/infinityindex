package com.wongislandd.infinityindex.comics.data

import com.wongislandd.infinityindex.comics.models.ComicsSortOption
import com.wongislandd.infinityindex.comics.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsRepository(okHttpClient: HttpClient) : NetworkClient(okHttpClient) {

    suspend fun getAllComics(
        start: Int,
        count: Int,
        searchParam: String?,
        sortOption: ComicsSortOption
    ): Resource<NetworkComicDataWrapper> {
        return get("public/comics") {
            parameter("offset", start)
            parameter("limit", count)
            searchParam?.also { searchParam ->
                parameter("titleStartsWith", searchParam)
            }
            parameter("orderBy", sortOption.sortKey)
        }
    }
}