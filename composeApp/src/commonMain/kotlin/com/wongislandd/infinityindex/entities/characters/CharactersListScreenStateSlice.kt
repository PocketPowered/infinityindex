package com.wongislandd.infinityindex.entities.characters

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice

class CharactersListScreenStateSlice: BaseListScreenStateSlice<Character>(
    EntityType.CHARACTERS
)