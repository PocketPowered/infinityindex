package com.wongislandd.infinityindex.infra.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkUrl(
    val type: String?,
    val url: String?
)