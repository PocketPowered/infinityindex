package com.wongislandd.infinityindex.entities.stories.models

import com.wongislandd.infinityindex.networking.util.SortOption

enum class StoriesSortOption(
    override val displayName: String,
    override val sortKey: String,
    override val isDefault: Boolean = false
) : SortOption {
    NAME_DESC(displayName = "Name (dec)", sortKey = "-name"),
    NAME_ASC(displayName = "Name (asc)", sortKey = "name"),
    MODIFIED_DESC(displayName = "Last Modified (dec)", sortKey = "-modified"),
    MODIFIED_ASC(displayName = "Last Modified (asc)", sortKey = "modified"),
}