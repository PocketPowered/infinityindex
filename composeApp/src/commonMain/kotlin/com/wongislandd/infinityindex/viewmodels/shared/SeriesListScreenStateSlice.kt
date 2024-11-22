package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class SeriesListScreenStateSlice(
    dataStoreRepository: DataStoreRepository
) : BaseListScreenStateSlice<Series>(
    EntityType.SERIES,
    dataStoreRepository
)