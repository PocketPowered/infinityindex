package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class ComicsListScreenStateSlice(
    dataStoreRepository: DataStoreRepository
): BaseListScreenStateSlice<Comic>(
    EntityType.COMICS,
    dataStoreRepository
)