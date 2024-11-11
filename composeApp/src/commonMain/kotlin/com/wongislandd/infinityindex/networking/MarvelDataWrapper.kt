package com.wongislandd.infinityindex.networking

import com.wongislandd.infinityindex.comics.list.models.ComicDataContainer

data class MarvelDataWrapper(
    val code: Int?,
    val status: String?,
    val data: ComicDataContainer,
)