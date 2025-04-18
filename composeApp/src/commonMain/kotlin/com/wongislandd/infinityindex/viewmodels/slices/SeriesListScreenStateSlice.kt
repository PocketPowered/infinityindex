package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.models.local.Series

class SeriesListScreenStateSlice : BaseListScreenStateSlice<Series>(
    EntityType.SERIES
)