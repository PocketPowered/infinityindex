package com.wongislandd.infinityindex.pillars.creators.data

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.creators.models.Creator
import com.wongislandd.infinityindex.pillars.creators.models.CreatorsSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.SearchQuery

class CreatorsPagingSource(
    private val creatorsRepository: CreatorsRepository,
    private val comicId: Int? = null,
    private val query: SearchQuery? = null,
    private val sortOption: CreatorsSortOption? = null
) : BasePagingSource<Creator>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Creator>> {
        return comicId?.let {
            creatorsRepository.getPagedCreatorsInComic(
                it,
                start,
                count
            )
        } ?: creatorsRepository.getAll(
            start,
            count,
            query?.text,
            sortOption?.sortKey
        )
    }
}