package com.wongislandd.infinityindex.networking.util

import kotlinx.serialization.Serializable

@Serializable
data class NetworkImage(
    val path: String?,
    val extension: String?
)