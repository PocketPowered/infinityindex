package com.wongislandd.infinityindex.networking.util

@kotlinx.serialization.Serializable
data class NetworkDataWrapper<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: NetworkDataContainer<T>,
    val etag: String?
)