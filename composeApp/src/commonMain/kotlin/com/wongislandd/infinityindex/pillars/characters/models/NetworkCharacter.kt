package com.wongislandd.infinityindex.pillars.characters.models

import com.wongislandd.infinityindex.networking.util.NetworkList
import com.wongislandd.infinityindex.networking.util.NetworkSummary
import com.wongislandd.infinityindex.networking.util.TypedNetworkSummary
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkImage
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkUrl
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCharacter(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val resourceURI: String? = null,
    val urls: List<NetworkUrl>? = null,
    val thumbnail: NetworkImage? = null,
    val comics: NetworkList<NetworkSummary>? = null,
    val stories: NetworkList<TypedNetworkSummary>? = null,
    val events: NetworkList<NetworkSummary>? = null,
    val series: NetworkList<NetworkSummary>? = null
)