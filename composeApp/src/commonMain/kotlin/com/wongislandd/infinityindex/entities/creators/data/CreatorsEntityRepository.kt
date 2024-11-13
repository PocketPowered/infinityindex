package com.wongislandd.infinityindex.entities.creators.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import com.wongislandd.infinityindex.entities.creators.transformers.CreatorTransformer
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class CreatorsEntityRepository(
    private val creatorTransformer: CreatorTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient), BasicEntityRepository<Creator> {

    override suspend fun getAll(
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

    override suspend fun get(
        id: Int
    ): Resource<Creator> {
        val response: Resource<NetworkDataWrapper<NetworkCreator>> =
            get("${SupportedPillars.CREATORS.basePath}/$id")
        return response.map { creatorTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    override suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Creator>> {
        val response: Resource<NetworkDataWrapper<NetworkCreator>> =
            get("comics/$comicId/creators") {
                parameter("offset", start)
                parameter("limit", count)
            }
        return response.map { creatorTransformer.transform(it) }
    }
}