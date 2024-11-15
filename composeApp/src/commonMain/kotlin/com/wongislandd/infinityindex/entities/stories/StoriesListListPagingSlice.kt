package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.repositories.StoriesEntityRepository

class StoriesListListPagingSlice(
    repository: StoriesEntityRepository
) : BaseListPagingSlice<NetworkStory, Story>(
    repository,
    EntityType.STORIES
)