package com.wongislandd.infinityindex.pillars.stories.data

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.stories.models.Story
import com.wongislandd.infinityindex.pillars.stories.models.StoriesSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.SearchQuery

class StoriesPagingSource(
    private val storiesRepository: StoriesRepository,
    private val comicId: Int? = null,
    private val query: SearchQuery? = null,
    private val sortOption: StoriesSortOption? = null
) : BasePagingSource<Story>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Story>> {
        return comicId?.let {
            storiesRepository.getPagedStoriesInComic(
                it,
                start,
                count
            )
        } ?: storiesRepository.getAll(
            start,
            count,
            query?.text,
            sortOption?.sortKey
        )
    }
}