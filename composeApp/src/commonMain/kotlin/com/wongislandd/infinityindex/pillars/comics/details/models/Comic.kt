package com.wongislandd.infinityindex.pillars.comics.details.models

data class Comic(
    val id: Int,
    val title: String,
    val imageUrl: String,
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
    val format: String?
)