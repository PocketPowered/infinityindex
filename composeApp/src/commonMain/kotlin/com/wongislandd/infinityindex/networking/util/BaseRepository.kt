package com.wongislandd.infinityindex.networking.util

import com.wongislandd.infinityindex.util.DataWrapperTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

abstract class BaseRepository<NETWORK_MODEL, LOCAL_MODEL>(
    private val transformer: DataWrapperTransformer<NETWORK_MODEL, LOCAL_MODEL>,
    private val endpoint: String, okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String,
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            get<NetworkDataWrapper<NETWORK_MODEL>>(endpoint) {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("titleStartsWith", searchParam)
                }
                parameter("orderBy", sortKey)
            }
        return response.map { transformer.transform(it) }
    }

    suspend fun get(
        id: Int
    ): Resource<LOCAL_MODEL> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            get<NetworkDataWrapper<NETWORK_MODEL>>("$endpoint/$id")
        return response.map { transformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }
}