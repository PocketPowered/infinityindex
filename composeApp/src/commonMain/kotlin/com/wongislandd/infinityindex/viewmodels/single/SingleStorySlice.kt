package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.models.network.NetworkStory
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.StoriesEntityRepository

class SingleStorySlice(
    storyRepository: StoriesEntityRepository
): BaseSingleEntityResolutionSlice<NetworkStory, Story>(
    EntityType.STORIES,
    storyRepository
)