package com.wongislandd.infinityindex.entities.series.models

import com.wongislandd.infinityindex.infra.util.SortOption

enum class SeriesSortOption(
    override val displayName: String,
    override val sortKey: String,
    override val isDefault: Boolean = false
) : SortOption {
    NAME_DESC(displayName = "Name (dec)", sortKey = "-name"),
    NAME_ASC(displayName = "Name (asc)", sortKey = "name"),
    MODIFIED_DESC(displayName = "Last Modified (dec)", sortKey = "-modified", isDefault = true),
    MODIFIED_ASC(displayName = "Last Modified (asc)", sortKey = "modified"),
}