package com.wongislandd.infinityindex.comics

import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicsRepository(okHttpClient: HttpClient) : NetworkClient(okHttpClient) {

    suspend fun getAllComics(
        start: Int,
        count: Int,
        sortOption: ComicsSortOption?
    ): Resource<NetworkComicDataWrapper> {
        return get("public/comics") {
            parameter("offset", start)
            parameter("limit", count)
            sortOption?.also {
                parameter("orderBy", it.sortKey)
            }
        }
    }
}