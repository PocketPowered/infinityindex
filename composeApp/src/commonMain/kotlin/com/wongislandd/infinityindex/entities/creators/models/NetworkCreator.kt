package com.wongislandd.infinityindex.entities.creators.models

import com.wongislandd.infinityindex.networking.util.NetworkList
import com.wongislandd.infinityindex.networking.util.NetworkSummary
import com.wongislandd.infinityindex.networking.util.TypedNetworkSummary
import com.wongislandd.infinityindex.networking.util.NetworkImage
import com.wongislandd.infinityindex.networking.util.NetworkUrl
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCreator(
    val id: Int?,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val suffix: String?,
    val fullName: String?,
    val modified: String?,
    val resourceURI: String?,
    val urls: List<NetworkUrl>?,
    val thumbnail: NetworkImage?,
    val comics: NetworkList<NetworkSummary>?,
    val stories: NetworkList<TypedNetworkSummary>?,
    val events: NetworkList<NetworkSummary>?,
    val series: NetworkList<NetworkSummary>?
)