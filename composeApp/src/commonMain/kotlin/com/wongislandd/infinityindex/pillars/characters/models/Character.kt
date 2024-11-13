package com.wongislandd.infinityindex.pillars.characters.models

import com.wongislandd.infinityindex.pillars.comics.details.models.RelatedLink

data class Character(
    val id: Int,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
)