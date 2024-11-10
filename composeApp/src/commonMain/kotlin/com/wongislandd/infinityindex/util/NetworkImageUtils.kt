package com.wongislandd.infinityindex.util

import com.wongislandd.infinityindex.networking.models.NetworkImage

fun NetworkImage.getFullUrl(): String {
    return "${path}.${extension}".replace("http://", "https://")
}