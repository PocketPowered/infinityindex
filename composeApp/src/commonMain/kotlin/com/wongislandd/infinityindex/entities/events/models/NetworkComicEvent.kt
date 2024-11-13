package com.wongislandd.infinityindex.entities.events.models

import com.wongislandd.infinityindex.networking.util.NetworkList
import com.wongislandd.infinityindex.networking.util.NetworkSummary
import com.wongislandd.infinityindex.networking.util.TypedNetworkSummary
import com.wongislandd.infinityindex.networking.util.NetworkImage
import com.wongislandd.infinityindex.networking.util.NetworkUrl
import kotlinx.serialization.Serializable

@Serializable
data class NetworkComicEvent(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val modified: String?,
    val start: String?,
    val end: String?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val series: NetworkList<NetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val characters: NetworkList<NetworkSummary>?,
    val creators: NetworkList<NetworkSummary>?,
    val next: NetworkSummary?,
    val previous: NetworkSummary?
)