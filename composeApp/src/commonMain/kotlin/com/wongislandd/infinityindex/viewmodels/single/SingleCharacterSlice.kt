package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.network.NetworkCharacter
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository

class SingleCharacterSlice(
    charactersRepository: CharactersEntityRepository
): BaseSingleEntityResolutionSlice<NetworkCharacter, Character>(
    EntityType.CHARACTERS,
    charactersRepository
)