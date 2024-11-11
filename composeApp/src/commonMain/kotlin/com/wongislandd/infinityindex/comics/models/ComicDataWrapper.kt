package com.wongislandd.infinityindex.comics.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkComicDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: NetworkComicDataContainer,
    val etag: String?
)

@Serializable
data class NetworkComicDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<NetworkComic>
)

@Serializable
data class NetworkComic(
    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val issueNumber: Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: String?,  // Adjusted for ISO 8601 date-time string (or use kotlinx.datetime.Instant)
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<NetworkTextObject>?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val series: NetworkSeriesSummary?,
    val variants: List<NetworkComicSummary>?,
    val collections: List<NetworkComicSummary>?,
    val collectedIssues: List<NetworkComicSummary>?,
    val dates: List<NetworkComicDate>?,
    val prices: List<NetworkComicPrice>?,
    val thumbnail: NetworkImage?,
    val images: List<NetworkImage>?,
    val creators: NetworkCreatorList?,
    val characters: NetworkCharacterList?,
    val stories: NetworkStoryList?,
    val events: NetworkEventList?
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
    val date: String? // Adjusted for ISO 8601 date-time string
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