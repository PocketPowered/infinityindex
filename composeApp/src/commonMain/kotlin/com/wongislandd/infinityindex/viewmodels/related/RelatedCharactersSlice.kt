package com.wongislandd.infinityindex.viewmodels.related

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType

class RelatedCharactersSlice(
    charactersRepository: CharactersEntityRepository
) : BaseRelatedEntitiesSlice<NetworkCharacter, Character>(
    charactersRepository,
    EntityType.CHARACTERS
)