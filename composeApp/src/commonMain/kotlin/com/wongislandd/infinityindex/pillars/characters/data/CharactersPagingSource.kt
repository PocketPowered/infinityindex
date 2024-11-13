package com.wongislandd.infinityindex.pillars.characters.data

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.characters.models.Character
import com.wongislandd.infinityindex.pillars.characters.models.CharactersSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.SearchQuery

class CharactersPagingSource(
    private val charactersRepository: CharactersRepository,
    private val comicId: Int? = null,
    private val query: SearchQuery? = null,
    private val sortOption: CharactersSortOption? = null
) : BasePagingSource<Character>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Character>> {
        return comicId?.let {
            charactersRepository.getPagedCharactersInComic(
                it,
                start,
                count
            )
        } ?: charactersRepository.getAll(
            start,
            count,
            query?.text,
            sortOption?.sortKey
        )
    }
}