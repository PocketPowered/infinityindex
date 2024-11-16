package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.network.NetworkStory
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.StoriesEntityRepository

class AllStoriesPagingSlice(
    repository: StoriesEntityRepository
) : BaseListPagingSlice<NetworkStory, Story>(
    repository,
    EntityType.STORIES,
    PagedListUseCase.ALL_AVAILABLE
)