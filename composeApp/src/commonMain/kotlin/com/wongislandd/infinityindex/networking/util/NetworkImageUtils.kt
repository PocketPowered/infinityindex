package com.wongislandd.infinityindex.networking.util

import com.wongislandd.infinityindex.comics.NetworkImage

fun NetworkImage.getFullUrl(): String {
    return "${path}.${extension}".replace("http://", "https://")
}