package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class StoriesListScreenStateSlice : BaseListScreenStateSlice<Story>(
    EntityType.STORIES
)