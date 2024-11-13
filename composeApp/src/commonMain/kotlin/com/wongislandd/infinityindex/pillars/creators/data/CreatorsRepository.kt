package com.wongislandd.infinityindex.pillars.creators.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.pillars.creators.transformers.CreatorTransformer
import com.wongislandd.infinityindex.pillars.creators.models.Creator
import com.wongislandd.infinityindex.pillars.creators.models.NetworkCreator
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class CreatorsRepository(
    private val creatorTransformer: CreatorTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?
    ): Resource<DataWrapper<Creator>> {
        val response: Resource<NetworkDataWrapper<NetworkCreator>> =
            get(SupportedPillars.CREATORS.basePath) {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("nameStartsWith", searchParam)
                }
                sortKey?.also { sortKey ->
                    parameter("orderBy", sortKey)
                }
            }
        return response.map { creatorTransformer.transform(it) }
    }

    suspend fun get(
        creatorId: Int
    ): Resource<Creator> {
        val response: Resource<NetworkDataWrapper<NetworkCreator>> = get("${SupportedPillars.CREATORS.basePath}/$creatorId")
        return response.map { creatorTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedCreatorsInComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Creator>> {
        val response: Resource<NetworkDataWrapper<NetworkCreator>> = get("comics/$comicId/Creators") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { creatorTransformer.transform(it) }
    }

    suspend fun getCreatorsInComic(
        comicId: Int
    ): Resource<DataWrapper<Creator>> {
        val response: Resource<NetworkDataWrapper<NetworkCreator>> = get("comics/$comicId/Creators")
        return response.map { creatorTransformer.transform(it) }
    }
}