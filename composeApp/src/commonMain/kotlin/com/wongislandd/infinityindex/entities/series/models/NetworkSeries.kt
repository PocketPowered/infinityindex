package com.wongislandd.infinityindex.entities.series.models

import com.wongislandd.infinityindex.networking.util.NetworkImage
import com.wongislandd.infinityindex.networking.util.NetworkList
import com.wongislandd.infinityindex.networking.util.NetworkSummary
import com.wongislandd.infinityindex.networking.util.NetworkUrl
import com.wongislandd.infinityindex.networking.util.TypedNetworkSummary
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSeries(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String?,
    val modified: String?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val characters: NetworkList<NetworkSummary>?,
    val creators: NetworkList<NetworkSummary>?,
    val next: NetworkSummary?,
    val previous: NetworkSummary?
)