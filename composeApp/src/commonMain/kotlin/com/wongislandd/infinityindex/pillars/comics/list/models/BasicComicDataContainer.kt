package com.wongislandd.infinityindex.pillars.comics.list.models

data class BasicComicDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<BasicComic>
)