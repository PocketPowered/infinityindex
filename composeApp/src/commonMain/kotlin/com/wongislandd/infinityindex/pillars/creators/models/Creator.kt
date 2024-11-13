package com.wongislandd.infinityindex.pillars.creators.models

import com.wongislandd.infinityindex.pillars.comics.details.models.RelatedLink

data class Creator(
    val id: Int,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
)