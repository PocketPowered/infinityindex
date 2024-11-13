package com.wongislandd.infinityindex.entities.events.models

import com.wongislandd.infinityindex.infra.networking.models.NetworkList
import com.wongislandd.infinityindex.infra.networking.models.NetworkSummary
import com.wongislandd.infinityindex.infra.networking.models.TypedNetworkSummary
import com.wongislandd.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.infinityindex.infra.networking.models.NetworkUrl
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