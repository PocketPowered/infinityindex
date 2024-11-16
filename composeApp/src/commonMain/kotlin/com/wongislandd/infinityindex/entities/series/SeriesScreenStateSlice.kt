package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class SeriesScreenStateSlice: BaseListScreenStateSlice<Series>(
    EntityType.SERIES
)