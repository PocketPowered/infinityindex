package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

class SeriesListPagingSlice(
    repository: SeriesEntityRepository
) : BaseListPagingSlice<NetworkSeries, Series>(
    repository,
    EntityType.SERIES
)