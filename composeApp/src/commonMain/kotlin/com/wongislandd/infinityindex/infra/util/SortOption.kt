package com.wongislandd.infinityindex.infra.util

interface SortOption {
    val displayName: String
    val sortKey: String
    val isDefault: Boolean
}