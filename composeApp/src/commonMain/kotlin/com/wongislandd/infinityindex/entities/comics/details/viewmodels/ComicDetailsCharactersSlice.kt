package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.characters.data.CharactersEntityRepository
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.infra.util.EntityType

class ComicDetailsCharactersSlice(
    charactersRepository: CharactersEntityRepository
) : BaseSlice<NetworkCharacter, Character>(
    charactersRepository,
    EntityType.CHARACTERS
)