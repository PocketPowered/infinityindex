package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class SeriesListScreenStateSlice : BaseListScreenStateSlice<Series>(
    EntityType.SERIES
)