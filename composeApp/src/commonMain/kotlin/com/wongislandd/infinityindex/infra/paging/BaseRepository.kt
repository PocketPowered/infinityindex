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
import io.ktor.util.reflect.TypeInfo

abstract class BaseRepository<NETWORK_MODEL: Any, LOCAL_MODEL>(
    private val transformer: DataWrapperTransformer<NETWORK_MODEL, LOCAL_MODEL>,
    okHttpClient: HttpClient, private val primaryEntityType: EntityType,
    private val typeInfo: TypeInfo
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?,
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest(primaryEntityType.key, typeInfo) {
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
            makeRequest("${primaryEntityType.key}/$id", typeInfo)
        return response.map { transformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedPrimaryEntityRelatedToOtherEntity(
        otherEntityType: EntityType,
        otherEntityId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest("${otherEntityType.key}/$otherEntityId/${primaryEntityType.key}", typeInfo) {
                parameter("offset", start)
                parameter("limit", count)
            }
        return response.map { transformer.transform(it) }
    }
}