package com.wongislandd.infinityindex.pillars.comics.details.models

import com.wongislandd.infinityindex.networking.util.Resource

data class ComicDetailsScreenState(
    val comicId: Int? = null,
    val detailedComicRes: Resource<DetailedComic> = Resource.Loading
)