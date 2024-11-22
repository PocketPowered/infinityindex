package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class StoriesListScreenStateSlice : BaseListScreenStateSlice<Story>(
    EntityType.STORIES,
)