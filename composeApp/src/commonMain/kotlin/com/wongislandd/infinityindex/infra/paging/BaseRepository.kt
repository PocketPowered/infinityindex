package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.infra.networking.NetworkClient
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.safeLet
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import kotlinx.serialization.KSerializer

abstract class BaseRepository<NETWORK_MODEL, LOCAL_MODEL>(
    private val transformer: DataWrapperTransformer<NETWORK_MODEL, LOCAL_MODEL>,
    okHttpClient: HttpClient, private val primaryEntityType: EntityType,
    networkModelSerializer: KSerializer<NETWORK_MODEL>
) : NetworkClient(okHttpClient) {

    private val networkDataWrapperSerializer: KSerializer<NetworkDataWrapper<NETWORK_MODEL>> =
        NetworkDataWrapper.serializer(networkModelSerializer)

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?,
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest(primaryEntityType.key, networkDataWrapperSerializer) {
                parameter("offset", start)
                parameter("limit", count)
                parameter("orderBy", sortKey)
                safeLet(
                    searchParam,
                    primaryEntityType.searchParamType?.key
                ) { searchParam, searchParamType ->
                    parameter(searchParamType, searchParam)
                }
            }
        return response.map { transformer.transform(it) }
    }

    suspend fun get(
        id: Int
    ): Resource<LOCAL_MODEL> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest("${primaryEntityType.key}/$id", networkDataWrapperSerializer)
        return response.map { transformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest("comics/$comicId/${primaryEntityType.key}", networkDataWrapperSerializer) {
                parameter("offset", start)
                parameter("limit", count)
            }
        return response.map { transformer.transform(it) }
    }
}