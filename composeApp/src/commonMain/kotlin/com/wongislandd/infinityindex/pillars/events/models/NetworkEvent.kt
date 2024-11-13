package com.wongislandd.infinityindex.pillars.events.models

import com.wongislandd.infinityindex.networking.util.NetworkList
import com.wongislandd.infinityindex.networking.util.NetworkSummary
import com.wongislandd.infinityindex.networking.util.TypedNetworkSummary
import com.wongislandd.infinityindex.networking.util.NetworkImage
import com.wongislandd.infinityindex.networking.util.NetworkUrl
import kotlinx.serialization.Serializable

@Serializable
data class NetworkEvent(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val series: NetworkList<NetworkSummary>?
)