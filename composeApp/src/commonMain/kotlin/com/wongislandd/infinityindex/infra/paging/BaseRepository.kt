package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.infra.networking.NetworkClient
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.util.ClientError
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SupportedPillars
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import kotlinx.serialization.KSerializer

// Running into issues with passing generics into the network client due to type erasure.
// Would be good to reuse this for each pillars repository, but giving up for now
abstract class BaseRepository<NETWORK_MODEL, LOCAL_MODEL>(
    val transformer: DataWrapperTransformer<NETWORK_MODEL, LOCAL_MODEL>,
    okHttpClient: HttpClient, val pillar: SupportedPillars,
    networkModelSerializer: KSerializer<NETWORK_MODEL>
) : NetworkClient(okHttpClient) {

    // Create a serializer for NetworkDataWrapper<NETWORK_MODEL> using the provided NETWORK_MODEL serializer
    val networkDataWrapperSerializer: KSerializer<NetworkDataWrapper<NETWORK_MODEL>> =
        NetworkDataWrapper.serializer(networkModelSerializer)

    suspend inline fun <reified : NETWORK_MODEL> getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?,
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            get(pillar.basePath, networkDataWrapperSerializer) {
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
            get("${pillar.basePath}/$id", networkDataWrapperSerializer)
        return response.map { transformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        return Resource.Error(ClientError.UNEXPECTED_STATE)
    }
}