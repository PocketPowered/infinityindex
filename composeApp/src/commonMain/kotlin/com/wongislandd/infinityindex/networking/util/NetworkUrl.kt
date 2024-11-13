package com.wongislandd.infinityindex.networking.util

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUrl(
    val type: String?,
    val url: String?
)