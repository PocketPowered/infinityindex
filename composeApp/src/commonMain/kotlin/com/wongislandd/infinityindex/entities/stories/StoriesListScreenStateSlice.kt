package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class StoriesListScreenStateSlice(
    dataStoreRepository: DataStoreRepository
) : BaseListScreenStateSlice<Story>(
    EntityType.STORIES,
    dataStoreRepository
)