package com.wongislandd.infinityindex.entities.characters.data

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.entities.characters.transformers.CharacterTransformer
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class CharactersEntityRepository(
    characterTransformer: CharacterTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkCharacter, Character>(
    characterTransformer,
    okHttpClient,
    EntityType.CHARACTERS,
    typeInfo<DataWrapper<NetworkCharacter>>()
)