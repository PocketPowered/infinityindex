package com.wongislandd.infinityindex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform