package com.wongislandd.infinityindex.viewmodels.shared

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class CharactersListScreenStateSlice: BaseListScreenStateSlice<Character>(
    EntityType.CHARACTERS
)