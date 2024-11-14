package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseResolutionSlice

class SeriesResolutionSlice(
    seriesRepository: SeriesEntityRepository
) : BaseResolutionSlice<NetworkSeries, Series>(
    seriesRepository,
    EntityType.SERIES
)