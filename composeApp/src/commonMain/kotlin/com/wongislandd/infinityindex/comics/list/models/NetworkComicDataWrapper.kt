package com.wongislandd.infinityindex.comics.list.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkComicDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: NetworkComicDataContainer?,
    val etag: String?
)