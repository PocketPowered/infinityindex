package com.wongislandd.infinityindex.entities.stories.data

import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.entities.stories.transformers.StoryTransformer
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.SupportedPillars
import io.ktor.client.HttpClient

class StoriesEntityRepository(
    storyTransformer: StoryTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkStory, Story>(
    storyTransformer,
    okHttpClient,
    SupportedPillars.STORIES,
    NetworkStory.serializer()
)