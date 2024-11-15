package com.wongislandd.infinityindex.infra.util

data class SelectableSortOption(
    val sortOption: SortOption,
    val isSelected: Boolean
)

fun List<SelectableSortOption>.isDefaultSelectionSorted(): Boolean {
    val defaultSelection = this.firstOrNull { it.sortOption.isDefault }
    return defaultSelection?.isSelected ?: false
}