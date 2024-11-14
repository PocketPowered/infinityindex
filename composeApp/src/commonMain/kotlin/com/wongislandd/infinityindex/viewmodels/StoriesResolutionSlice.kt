package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseResolutionSlice

class StoriesResolutionSlice(
    storiesRepository: StoriesEntityRepository
) : BaseResolutionSlice<NetworkStory, Story>(
    storiesRepository,
    EntityType.STORIES
)