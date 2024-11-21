package com.wongislandd.infinityindex.infra.paging

import com.wongislandd.infinityindex.ComicConstants
import com.wongislandd.infinityindex.infra.networking.NetworkClient
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.Resource.Loading.onSuccess
import com.wongislandd.infinityindex.infra.util.safeLet
import com.wongislandd.infinityindex.infra.viewmodels.AppLeveled
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.util.reflect.TypeInfo

abstract class BaseRepository<NETWORK_MODEL, LOCAL_MODEL : EntityModel>(
    private val transformer: DataWrapperTransformer<NETWORK_MODEL, LOCAL_MODEL>,
    okHttpClient: HttpClient,
    private val rootEntityType: EntityType,
    private val typeInfo: TypeInfo
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?,
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest(rootEntityType.key, typeInfo) {
                parameter("offset", start)
                parameter("limit", count)
                sortKey?.also {
                    parameter("orderBy", it)
                }
                safeLet(
                    searchParam?.takeIf { it.isNotBlank() },
                    rootEntityType.searchParamType?.key
                ) { searchParam, searchParamType ->
                    parameter(searchParamType, searchParam)
                }
                if (rootEntityType == EntityType.COMICS) {
                    parameter("dateRange", ComicConstants.PREDEFINED_DATE_RANGE)
                }
            }
        response.onSuccess { AppLeveled.updateAttributionText(it.attributionText) }
        return response.map { transformer.transform(it) }
    }

    suspend fun get(
        id: Int
    ): Resource<LOCAL_MODEL> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest("${rootEntityType.key}/$id", typeInfo)
        response.onSuccess { AppLeveled.updateAttributionText(it.attributionText) }
        return response.map { transformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    /**
     * If this is the Comics Repository, I can use this to find comics
     * related to another entity. This is designed this way so that this repository
     * only needs to know how to transform one type of entity.
     */
    suspend fun getPagedPrimaryEntityRelatedToOtherEntity(
        relatedEntityType: EntityType,
        relatedEntityId: Int,
        searchParam: String? = null,
        sortKey: String? = null,
        start: Int,
        count: Int
    ): Resource<DataWrapper<LOCAL_MODEL>> {
        val response: Resource<NetworkDataWrapper<NETWORK_MODEL>> =
            makeRequest(
                "${relatedEntityType.key}/$relatedEntityId/${rootEntityType.key}",
                typeInfo
            ) {
                parameter("offset", start)
                parameter("limit", count)
                parameter("orderBy", sortKey)
                safeLet(
                    searchParam?.takeIf { it.isNotBlank() },
                    rootEntityType.searchParamType?.key
                ) { searchParam, searchParamType ->
                    parameter(searchParamType, searchParam)
                }
            }
        response.onSuccess { AppLeveled.updateAttributionText(it.attributionText) }
        return response.map { transformer.transform(it) }
    }
}