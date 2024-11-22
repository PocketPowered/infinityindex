package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class CharactersListScreenStateSlice(
    dataStore: DataStoreRepository
): BaseListScreenStateSlice<Character>(
    EntityType.CHARACTERS,
    dataStore
)