package com.wongislandd.infinityindex.entities.stories.data

import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.entities.stories.transformers.StoryTransformer
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class StoriesEntityRepository(
    storyTransformer: StoryTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkStory, Story>(
    storyTransformer,
    okHttpClient,
    EntityType.STORIES,
    typeInfo<DataWrapper<NetworkStory>>()
)