package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.models.local.Creator

class CreatorsListScreenStateSlice: BaseListScreenStateSlice<Creator>(
    EntityType.CREATORS
)