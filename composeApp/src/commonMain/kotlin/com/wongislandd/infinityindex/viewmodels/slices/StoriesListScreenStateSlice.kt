package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class StoriesListScreenStateSlice : BaseListScreenStateSlice<Story>(
    EntityType.STORIES,
)