package com.wongislandd.infinityindex.pillars.characters.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.characters.models.CharactersSortOption
import com.wongislandd.infinityindex.pillars.characters.transformers.CharacterTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class CharactersRepository(
    private val characterTransformer: CharacterTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getCharacters(
        start: Int,
        count: Int,
        searchParam: String?,
        sortOption: CharactersSortOption
    ): Resource<DataWrapper<Character>> {
        val response = get<DataWrapper<Character>>("public/characters") {
            parameter("offset", start)
            parameter("limit", count)
            searchParam?.also { searchParam ->
                parameter("nameStartsWith", searchParam)
            }
            parameter("orderBy", sortOption.sortKey)
        }
        return response
    }


}