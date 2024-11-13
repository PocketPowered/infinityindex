package com.wongislandd.infinityindex.entities.events.models

import com.wongislandd.infinityindex.util.DisplayableEntity

data class ComicEvent(
    override val id: Int,
    override val displayName: String,
    override val imageUrl: String,
    val description: String?,
    val start: String?,
    val end: String?
): DisplayableEntity