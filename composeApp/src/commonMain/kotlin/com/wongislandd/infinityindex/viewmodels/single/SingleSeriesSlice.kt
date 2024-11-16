package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

class SingleSeriesSlice(
    seriesRepository: SeriesEntityRepository
): BaseSingleEntityResolutionSlice<NetworkSeries, Series>(
    EntityType.SERIES,
    seriesRepository
)