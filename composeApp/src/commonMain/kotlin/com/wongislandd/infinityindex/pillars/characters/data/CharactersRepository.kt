package com.wongislandd.infinityindex.pillars.characters.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.pillars.characters.transformers.CharacterTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class CharactersRepository(
    private val characterTransformer: CharacterTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String
    ): Resource<DataWrapper<Character>> {
        val response: Resource<NetworkDataWrapper<NetworkCharacter>> =
            get("comics") {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("nameStartsWith", searchParam)
                }
                parameter("orderBy", sortKey)
            }
        return response.map { characterTransformer.transform(it) }
    }

    suspend fun get(
        comicId: Int
    ): Resource<Character> {
        val response: Resource<NetworkDataWrapper<NetworkCharacter>> = get("comics/$comicId")
        return response.map { characterTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }


}