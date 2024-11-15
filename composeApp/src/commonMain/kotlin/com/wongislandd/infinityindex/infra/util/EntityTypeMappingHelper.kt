package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.entities.characters.models.CharactersSortOption
import com.wongislandd.infinityindex.entities.comics.list.models.ComicsSortOption
import com.wongislandd.infinityindex.entities.creators.models.CreatorsSortOption
import com.wongislandd.infinityindex.entities.events.models.EventsSortOption
import com.wongislandd.infinityindex.entities.series.models.SeriesSortOption
import com.wongislandd.infinityindex.entities.stories.models.StoriesSortOption

fun EntityType.getDefaultSortOption(): SortOption {
    val discoveredEntityType = when (this) {
        EntityType.COMICS -> ComicsSortOption.entries.find { it.isDefault }
        EntityType.CHARACTERS -> CharactersSortOption.entries.find { it.isDefault }
        EntityType.CREATORS -> CreatorsSortOption.entries.find { it.isDefault }
        EntityType.EVENTS -> EventsSortOption.entries.find { it.isDefault }
        EntityType.SERIES -> SeriesSortOption.entries.find { it.isDefault }
        EntityType.STORIES -> StoriesSortOption.entries.find { it.isDefault }
    }
    if (discoveredEntityType == null) {
        throw IllegalStateException("No default sort option found for entity type $this")
    }
    return discoveredEntityType
}

fun EntityType.getSortOptions(): List<SortOption> {
    return when (this) {
        EntityType.COMICS -> ComicsSortOption.entries.toList()
        EntityType.CHARACTERS -> CharactersSortOption.entries.toList()
        EntityType.CREATORS -> CreatorsSortOption.entries.toList()
        EntityType.EVENTS -> EventsSortOption.entries.toList()
        EntityType.SERIES -> SeriesSortOption.entries.toList()
        EntityType.STORIES -> StoriesSortOption.entries.toList()
    }
}