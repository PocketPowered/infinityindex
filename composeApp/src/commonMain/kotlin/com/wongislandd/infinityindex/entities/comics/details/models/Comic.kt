package com.wongislandd.infinityindex.entities.comics.details.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.util.DisplayableEntity

data class Comic(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    val pageCount: Int?,
    val issueNumber: Double?,
    val lastModified: String?,
    val relatedDates: List<RelatedDate>,
    val relatedTexts: List<RelatedText>,
    val relatedPrices: List<RelatedPrice>,
    val relatedLinks: List<RelatedLink>,
    val variantDescription: String?,
    val description: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val hasEvents: Boolean,
    val hasStories: Boolean,
    val hasCharacters: Boolean,
    val hasCreators: Boolean
): DisplayableEntity