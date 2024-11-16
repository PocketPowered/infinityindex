package com.wongislandd.infinityindex.repositories

import com.wongislandd.infinityindex.models.network.NetworkStory
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.transformers.StoryTransformer
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
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
    typeInfo<NetworkDataWrapper<NetworkStory>>()
)