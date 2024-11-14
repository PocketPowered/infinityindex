package com.wongislandd.infinityindex.entities.events.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.util.DisplayableEntity

data class ComicEvent(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    val description: String?,
    val start: String?,
    val end: String?
): DisplayableEntity