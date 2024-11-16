package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

class SingleSeriesSlice(
    seriesRepository: SeriesEntityRepository
): BaseSingleEntityResolutionSlice<NetworkSeries, Series>(
    EntityType.SERIES,
    seriesRepository
)