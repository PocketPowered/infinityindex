package com.wongislandd.infinityindex.comics.details.models

data class DetailedComicDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<DetailedComic>
)