package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.infinityindex.models.network.NetworkStory
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedStoriesSlice(
    storiesRepository: StoriesEntityRepository
) : BaseListPagingSlice<NetworkStory, Story>(
    storiesRepository,
    EntityType.STORIES,
    PagedListUseCase.RELATED_ENTITIES
)