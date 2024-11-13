package com.wongislandd.infinityindex.entities.characters.data

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.entities.characters.transformers.CharacterTransformer
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient

class CharactersEntityRepository(
    characterTransformer: CharacterTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkCharacter, Character>(
    characterTransformer,
    okHttpClient,
    EntityType.CHARACTERS,
    NetworkCharacter.serializer()
)