package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class CreatorsListScreenStateSlice(
    dataStoreRepository: DataStoreRepository
): BaseListScreenStateSlice<Creator>(
    EntityType.CREATORS,
    dataStoreRepository
)