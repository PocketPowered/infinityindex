package com.wongislandd.infinityindex.infra.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkImage(
    val path: String?,
    val extension: String?
)