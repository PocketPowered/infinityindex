package com.wongislandd.infinityindex.settings

interface Setting<T : Any> {
    val displayName: String
    val description: String
    val key: String
    val defaultValue: T
}