package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.stories.data.StoriesEntityRepository
import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType

class ComicDetailsStoriesSlice(
    storiesRepository: StoriesEntityRepository
) : BaseSlice<NetworkStory,Story>(
    storiesRepository,
    EntityType.STORIES
)