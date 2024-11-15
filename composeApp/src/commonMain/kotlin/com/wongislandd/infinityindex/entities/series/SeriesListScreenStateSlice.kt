package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class SeriesListScreenStateSlice : BaseListScreenStateSlice<Series>(
    EntityType.SERIES
)