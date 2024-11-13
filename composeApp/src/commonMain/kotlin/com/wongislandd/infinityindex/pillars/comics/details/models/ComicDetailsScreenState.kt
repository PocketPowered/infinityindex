package com.wongislandd.infinityindex.pillars.comics.details.models

import app.cash.paging.PagingData
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.characters.models.Character
import kotlinx.coroutines.flow.StateFlow

data class ComicDetailsScreenState(
    val comicId: Int? = null,
    val comicRes: Resource<Comic> = Resource.Loading,
    val characterData: StateFlow<PagingData<Character>>
)