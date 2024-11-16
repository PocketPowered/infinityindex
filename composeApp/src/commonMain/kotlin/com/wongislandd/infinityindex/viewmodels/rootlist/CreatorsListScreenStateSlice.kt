package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class CreatorsListScreenStateSlice: BaseListScreenStateSlice<Creator>(
    EntityType.CREATORS
)