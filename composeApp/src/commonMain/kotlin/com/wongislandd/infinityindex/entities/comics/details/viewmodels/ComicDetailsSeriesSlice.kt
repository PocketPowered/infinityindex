package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.series.data.SeriesEntityRepository
import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.EntityType

class ComicDetailsSeriesSlice(
    seriesRepository: SeriesEntityRepository
) : BaseSlice<NetworkSeries, Series>(
    seriesRepository,
    EntityType.SERIES
)