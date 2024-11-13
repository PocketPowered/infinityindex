package com.wongislandd.infinityindex.networking.util

import kotlinx.serialization.Serializable

@Serializable
data class NetworkList<T>(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<T>?
)

fun <T> NetworkList<T>?.hasItems(): Boolean = this?.items?.isNotEmpty() ?: false