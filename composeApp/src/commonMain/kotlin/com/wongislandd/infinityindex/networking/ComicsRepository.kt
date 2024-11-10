package com.wongislandd.infinityindex.networking

import com.wongislandd.infinityindex.networking.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.util.NetworkClient
import com.wongislandd.infinityindex.util.Resource
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.StateFlow

class ComicsRepository(okHttpClient: HttpClient): NetworkClient(okHttpClient) {

    suspend fun getAllComics(): StateFlow<Resource<NetworkComicDataWrapper>> {
        return get("public/comics")
    }
}