package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedSeriesSlice(
    seriesRepository: SeriesEntityRepository
) : BaseListPagingSlice<NetworkSeries, Series>(
    seriesRepository,
    EntityType.SERIES,
    PagedListUseCase.RELATED_ENTITIES
)