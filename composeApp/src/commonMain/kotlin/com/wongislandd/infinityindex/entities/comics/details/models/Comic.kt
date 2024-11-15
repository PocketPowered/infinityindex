package com.wongislandd.infinityindex.entities.comics.details.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.util.EntityModel

data class Comic(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    val pageCount: Int?,
    val issueNumber: Double?,
    override val lastModified: String,
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
    override val hasEvents: Boolean,
    override val hasStories: Boolean,
    override val hasCharacters: Boolean,
    override val hasCreators: Boolean,
    override val hasComics: Boolean,
    override val hasSeries: Boolean
): EntityModel