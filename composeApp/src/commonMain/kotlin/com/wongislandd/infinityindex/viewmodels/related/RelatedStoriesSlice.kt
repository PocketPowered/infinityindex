package com.wongislandd.infinityindex.viewmodels.related

import com.wongislandd.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType

class RelatedStoriesSlice(
    storiesRepository: StoriesEntityRepository
) : BaseRelatedEntitiesSlice<NetworkStory, Story>(
    storiesRepository,
    EntityType.STORIES
)