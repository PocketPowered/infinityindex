package com.wongislandd.infinityindex.pillars.characters.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
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
        sortKey: String?
    ): Resource<DataWrapper<Character>> {
        val response: Resource<NetworkDataWrapper<NetworkCharacter>> =
            get(SupportedPillars.CHARACTERS.basePath) {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("nameStartsWith", searchParam)
                }
                sortKey?.also { sortKey ->
                    parameter("orderBy", sortKey)
                }
            }
        return response.map { characterTransformer.transform(it) }
    }

    suspend fun get(
        characterId: Int
    ): Resource<Character> {
        val response: Resource<NetworkDataWrapper<NetworkCharacter>> = get("${SupportedPillars.CHARACTERS.basePath}/$characterId")
        return response.map { characterTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedCharactersInComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Character>> {
        val response: Resource<NetworkDataWrapper<NetworkCharacter>> = get("comics/$comicId/characters") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { characterTransformer.transform(it) }
    }

    suspend fun getCharactersInComic(
        comicId: Int
    ): Resource<DataWrapper<Character>> {
        val response: Resource<NetworkDataWrapper<NetworkCharacter>> = get("comics/$comicId/characters")
        return response.map { characterTransformer.transform(it) }
    }


}