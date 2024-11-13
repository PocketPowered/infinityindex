package com.wongislandd.infinityindex.entities.characters.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.entities.characters.transformers.CharacterTransformer
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class CharactersEntityRepository(
    private val characterTransformer: CharacterTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient), BasicEntityRepository<Character> {

    override suspend fun getAll(
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

    override suspend fun get(
        id: Int
    ): Resource<Character> {
        val response: Resource<NetworkDataWrapper<NetworkCharacter>> = get("${SupportedPillars.CHARACTERS.basePath}/$id")
        return response.map { characterTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    override suspend fun getPagedEntityFromComic(
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
}