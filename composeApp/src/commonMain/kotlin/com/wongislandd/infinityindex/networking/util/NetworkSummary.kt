package com.wongislandd.infinityindex.networking.util

import kotlinx.serialization.Serializable

@Serializable
data class NetworkSummary(
    val resourceURI: String?,
    val name: String?,
)

@Serializable
data class TypedNetworkSummary(
    val resourceURI: String?,
    val name: String?,
    val type: String?
)