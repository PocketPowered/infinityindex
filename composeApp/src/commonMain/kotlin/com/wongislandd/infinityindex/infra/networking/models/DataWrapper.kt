package com.wongislandd.infinityindex.infra.networking.models

// Simplified concrete DataWrapper class
data class DataWrapper<T>(
    val code: Int,
    val status: String,
    val data: DataContainer<T>
)