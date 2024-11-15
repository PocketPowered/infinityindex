package com.wongislandd.infinityindex.entities.creators

import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class CreatorsListScreenStateSlice: BaseListScreenStateSlice<Creator>(
    EntityType.CREATORS
)