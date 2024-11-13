package com.wongislandd.infinityindex.pillars.series.data

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.series.models.Series
import com.wongislandd.infinityindex.pillars.series.models.SeriesSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.SearchQuery

class SeriesPagingSource(
    private val seriesRepository: SeriesRepository,
    private val comicId: Int? = null,
    private val query: SearchQuery? = null,
    private val sortOption: SeriesSortOption? = null
) : BasePagingSource<Series>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Series>> {
        return comicId?.let {
            seriesRepository.getPagedSeriesInComic(
                it,
                start,
                count
            )
        } ?: seriesRepository.getAll(
            start,
            count,
            query?.text,
            sortOption?.sortKey
        )
    }
}