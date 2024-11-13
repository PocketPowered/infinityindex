package com.wongislandd.infinityindex.entities.stories.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.entities.stories.transformers.StoryTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class StoriesEntityRepository(
    private val storyTransformer: StoryTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient), BasicEntityRepository<Story> {

    override suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?
    ): Resource<DataWrapper<Story>> {
        val response: Resource<NetworkDataWrapper<NetworkStory>> =
            get(SupportedPillars.CHARACTERS.basePath) {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("nameStartsWith", searchParam)
                }
                sortKey?.also { sortKey ->
                    parameter("orderBy", sortKey)
                }
            }
        return response.map { storyTransformer.transform(it) }
    }

    override suspend fun get(
        id: Int
    ): Resource<Story> {
        val response: Resource<NetworkDataWrapper<NetworkStory>> = get("${SupportedPillars.CHARACTERS.basePath}/$id")
        return response.map { storyTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    override suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Story>> {
        val response: Resource<NetworkDataWrapper<NetworkStory>> = get("comics/$comicId/stories") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { storyTransformer.transform(it) }
    }
}