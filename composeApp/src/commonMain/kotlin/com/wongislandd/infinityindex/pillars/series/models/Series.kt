package com.wongislandd.infinityindex.pillars.series.models

import com.wongislandd.infinityindex.pillars.comics.details.models.RelatedLink

data class Series(
    val id: Int,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
)