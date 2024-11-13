package com.wongislandd.infinityindex.pillars.comics.list.models

import com.wongislandd.infinityindex.networking.util.NetworkImage
import com.wongislandd.infinityindex.networking.util.NetworkList
import com.wongislandd.infinityindex.networking.util.NetworkSummary
import com.wongislandd.infinityindex.networking.util.NetworkUrl
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
data class NetworkComicDate(
    val type: String?,
    val date: String?
)

@Serializable
data class NetworkComicPrice(
    val type: String?,
    val price: Float?
)




