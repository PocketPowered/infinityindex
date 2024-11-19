package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.sortoptions.CharactersSortOption
import com.wongislandd.infinityindex.sortoptions.ComicsSortOption
import com.wongislandd.infinityindex.sortoptions.CreatorsSortOption
import com.wongislandd.infinityindex.sortoptions.EventsSortOption
import com.wongislandd.infinityindex.sortoptions.SeriesSortOption
import com.wongislandd.infinityindex.sortoptions.StoriesSortOption

fun EntityType.getDefaultSortOption(): SortOption? {
    val discoveredSortOption = when (this) {
        EntityType.COMICS -> ComicsSortOption.entries.find { it.isDefault }
        EntityType.CHARACTERS -> CharactersSortOption.entries.find { it.isDefault }
        EntityType.CREATORS -> CreatorsSortOption.entries.find { it.isDefault }
        EntityType.EVENTS -> EventsSortOption.entries.find { it.isDefault }
        EntityType.SERIES -> SeriesSortOption.entries.find { it.isDefault }
        EntityType.STORIES -> StoriesSortOption.entries.find { it.isDefault }
    }
    return discoveredSortOption
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

fun EntityModel.getEntityType(): EntityType {
    return when (this) {
        is Comic -> EntityType.COMICS
        is Character -> EntityType.CHARACTERS
        is Creator -> EntityType.CREATORS
        is Event -> EntityType.EVENTS
        is Series -> EntityType.SERIES
        is Story -> EntityType.STORIES
        else -> throw IllegalStateException("Unknown entity type for $this")
    }
}