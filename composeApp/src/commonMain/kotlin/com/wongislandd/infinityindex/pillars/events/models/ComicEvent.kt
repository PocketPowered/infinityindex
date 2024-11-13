package com.wongislandd.infinityindex.pillars.events.models

import com.wongislandd.infinityindex.pillars.comics.details.models.RelatedLink

data class ComicEvent(
    val id: Int,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
)