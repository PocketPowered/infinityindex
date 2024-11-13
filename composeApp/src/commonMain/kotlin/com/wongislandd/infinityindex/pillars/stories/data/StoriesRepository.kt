package com.wongislandd.infinityindex.pillars.stories.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.pillars.stories.models.Story
import com.wongislandd.infinityindex.pillars.stories.models.NetworkStory
import com.wongislandd.infinityindex.pillars.stories.transformers.StoryTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class StoriesRepository(
    private val storyTransformer: StoryTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
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

    suspend fun get(
        characterId: Int
    ): Resource<Story> {
        val response: Resource<NetworkDataWrapper<NetworkStory>> = get("${SupportedPillars.CHARACTERS.basePath}/$characterId")
        return response.map { storyTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedStoriesInComic(
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

    suspend fun getStoriesInComic(
        comicId: Int
    ): Resource<DataWrapper<Story>> {
        val response: Resource<NetworkDataWrapper<NetworkStory>> = get("comics/$comicId/stories")
        return response.map { storyTransformer.transform(it) }
    }


}