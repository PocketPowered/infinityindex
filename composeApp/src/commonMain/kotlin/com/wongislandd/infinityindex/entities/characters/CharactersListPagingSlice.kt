package com.wongislandd.infinityindex.entities.characters

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository

class CharactersListPagingSlice(
    repository: CharactersEntityRepository
): BaseListPagingSlice<NetworkCharacter, Character>(
    repository,
    EntityType.CHARACTERS
)