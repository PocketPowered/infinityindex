package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseResolutionSlice

class CharactersResolutionSlice(
    charactersRepository: CharactersEntityRepository
) : BaseResolutionSlice<NetworkCharacter, Character>(
    charactersRepository,
    EntityType.CHARACTERS
)