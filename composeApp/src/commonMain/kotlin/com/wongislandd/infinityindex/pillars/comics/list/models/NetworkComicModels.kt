package com.wongislandd.infinityindex.pillars.comics.list.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkComicDataContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<NetworkComic>?
)

@Serializable
data class NetworkComic(
    val id: Int? = null,
    val digitalId: Int? = null,
    val title: String? = null,
    val issueNumber: Double? = null,
    val variantDescription: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val isbn: String? = null,
    val upc: String? = null,
    val diamondCode: String? = null,
    val ean: String? = null,
    val issn: String? = null,
    val format: String? = null,
    val pageCount: Int? = null,
    val textObjects: List<NetworkTextObject>? = null,
    val resourceURI: String? = null,
    val urls: List<NetworkUrl>? = null,
    val series: NetworkSeriesSummary? = null,
    val variants: List<NetworkComicSummary>? = null,
    val collections: List<NetworkComicSummary>? = null,
    val collectedIssues: List<NetworkComicSummary>? = null,
    val dates: List<NetworkComicDate>? = null,
    val prices: List<NetworkComicPrice>? = null,
    val thumbnail: NetworkImage? = null,
    val images: List<NetworkImage>? = null,
    val creators: NetworkCreatorList? = null,
    val characters: NetworkCharacterList? = null,
    val stories: NetworkStoryList? = null,
    val events: NetworkEventList? = null
)

@Serializable
data class NetworkTextObject(
    val type: String?,
    val language: String?,
    val text: String?
)

@Serializable
data class NetworkUrl(
    val type: String?,
    val url: String?
)

@Serializable
data class NetworkSeriesSummary(
    val resourceURI: String?,
    val name: String?
)

@Serializable
data class NetworkComicSummary(
    val resourceURI: String?,
    val name: String?
)

@Serializable
data class NetworkComicDate(
    val type: String?,
    val date: String?
)

@Serializable
data class NetworkComicPrice(
    val type: String?,
    val price: Float?
)

@Serializable
data class NetworkImage(
    val path: String?,
    val extension: String?
)

@Serializable
data class NetworkCreatorList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkCreatorSummary>?
)

@Serializable
data class NetworkCreatorSummary(
    val resourceURI: String?,
    val name: String?,
    val role: String?
)

@Serializable
data class NetworkCharacterList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkCharacterSummary>?
)

@Serializable
data class NetworkCharacterSummary(
    val resourceURI: String?,
    val name: String?,
    val role: String?
)

@Serializable
data class NetworkStoryList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkStorySummary>?
)

@Serializable
data class NetworkStorySummary(
    val resourceURI: String?,
    val name: String?,
    val type: String?
)

@Serializable
data class NetworkEventList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkEventSummary>?
)

@Serializable
data class NetworkEventSummary(
    val resourceURI: String?,
    val name: String?
)