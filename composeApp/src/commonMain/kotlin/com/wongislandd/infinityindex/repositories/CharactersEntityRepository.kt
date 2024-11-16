package com.wongislandd.infinityindex.repositories

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.network.NetworkCharacter
import com.wongislandd.infinityindex.transformers.CharacterTransformer
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
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
    typeInfo<NetworkDataWrapper<NetworkCharacter>>()
)