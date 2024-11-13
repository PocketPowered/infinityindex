package com.wongislandd.infinityindex.entities.characters.data

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.entities.characters.transformers.CharacterTransformer
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.SupportedPillars
import io.ktor.client.HttpClient

class CharactersEntityRepository(
    characterTransformer: CharacterTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkCharacter, Character>(
    characterTransformer,
    okHttpClient,
    SupportedPillars.CHARACTERS,
    NetworkCharacter.serializer()
)