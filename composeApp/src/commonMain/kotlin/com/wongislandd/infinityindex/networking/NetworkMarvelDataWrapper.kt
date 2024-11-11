package com.wongislandd.infinityindex.networking

import com.wongislandd.infinityindex.comics.list.models.NetworkComicDataContainer
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMarvelDataWrapper(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: NetworkComicDataContainer?,
    val etag: String?
)