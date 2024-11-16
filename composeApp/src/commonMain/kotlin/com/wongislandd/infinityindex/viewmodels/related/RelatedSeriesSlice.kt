package com.wongislandd.infinityindex.viewmodels.related

import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.EntityType

class RelatedSeriesSlice(
    seriesRepository: SeriesEntityRepository
) : BaseRelatedEntitiesSlice<NetworkSeries, Series>(
    seriesRepository,
    EntityType.SERIES
)