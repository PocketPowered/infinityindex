package com.wongislandd.infinityindex.networking.util

data class PaginationItems<T>(
    val items: List<T>,
    val start: Int,
    val count: Int,
    val total: Long
)