package com.wongislandd.infinityindex.util

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: com.wongislandd.infinityindex.util.Error?) : Resource<Nothing>()
}