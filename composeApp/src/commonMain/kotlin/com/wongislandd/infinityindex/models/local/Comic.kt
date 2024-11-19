package com.wongislandd.infinityindex.models.local

import com.wongislandd.infinityindex.entities.comics.models.RelatedDate
import com.wongislandd.infinityindex.entities.comics.models.RelatedLink
import com.wongislandd.infinityindex.entities.comics.models.RelatedPrice
import com.wongislandd.infinityindex.entities.comics.models.RelatedText
import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.networking.models.EntityReference
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
    val comicCreatorsByRole: Map<String, List<String>>,
    val coverCreatorsByRole: Map<String, List<String>>,
    val seriesEntityReference: EntityReference?,
    override val relatedEventsCount: Int,
    override val relatedStoriesCount: Int,
    override val relatedCharactersCount: Int,
    override val relatedCreatorsCount: Int,
    override val relatedComicsCount: Int,
    override val relatedSeriesCount: Int
): EntityModel