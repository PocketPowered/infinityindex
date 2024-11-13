package com.wongislandd.infinityindex.networking.util

// Simplified concrete DataWrapper class
data class DataWrapper<T>(
    val code: Int,
    val status: String,
    val data: DataContainer<T>
)