package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

class SeriesSupplementaryEntityResolutionSlice(
    seriesRepository: SeriesEntityRepository
) : BaseSupplementaryEntityResolutionSlice<Series>(
    seriesRepository,
    EntityType.SERIES
)