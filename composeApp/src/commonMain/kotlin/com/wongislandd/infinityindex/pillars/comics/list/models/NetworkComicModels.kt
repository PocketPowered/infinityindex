package com.wongislandd.infinityindex.pillars.comics.list.models

import com.wongislandd.infinityindex.networking.util.NetworkList
import com.wongislandd.infinityindex.networking.util.NetworkSummary
import com.wongislandd.infinityindex.networking.util.TypedNetworkSummary
import kotlinx.serialization.Serializable

@Serializable
data class NetworkComic(
    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val issueNumber: Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: String?,
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
    val series: NetworkSummary?,
    val variants: List<NetworkSummary>?,
    val collections: List<NetworkSummary>?,
    val collectedIssues: List<NetworkSummary>?,
    val dates: List<NetworkComicDate>?,
    val prices: List<NetworkComicPrice>?,
    val thumbnail: NetworkImage?,
    val images: List<NetworkImage>?,
    val creators: NetworkList<NetworkSummary>?,
    val characters: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val events: NetworkList<NetworkSummary>?
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
data class NetworkCharacterList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkCharacterSummary>?
)

@Serializable
data class NetworkCreatorList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkCreatorSummary>?
)


@Serializable
data class NetworkStoryList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkStorySummary>?
)


@Serializable
data class NetworkEventList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<NetworkEventSummary>?
)



@Serializable
data class NetworkCharacterSummary(
    val resourceURI: String?,
    val name: String?,
    val role: String?
)

@Serializable
data class NetworkStorySummary(
    val resourceURI: String?,
    val name: String?,
    val type: String?
)

@Serializable
data class NetworkEventSummary(
    val resourceURI: String?,
    val name: String?
)


@Serializable
data class NetworkCreatorSummary(
    val resourceURI: String?,
    val name: String?,
    val role: String?
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





