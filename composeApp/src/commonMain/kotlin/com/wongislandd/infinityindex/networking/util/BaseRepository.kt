package com.wongislandd.infinityindex.networking.util

import com.wongislandd.infinityindex.util.DataWrapperTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

// Running into issues with passing generics into the network client due to type erasure.
// Would be good to reuse this for each pillars repository, but giving up for now
abstract class BaseRepository<NETWORK_MODEL, LOCAL_MODEL>(
    private val transformer: DataWrapperTransformer<NETWORK_MODEL, LOCAL_MODEL>,
    private val pillar: SupportedPillars, okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String,
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            get<NetworkDataWrapper<NETWORK_MODEL>>(pillar.basePath) {
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
            get<NetworkDataWrapper<NETWORK_MODEL>>("${pillar.basePath}/$id")
        return response.map { transformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }
}