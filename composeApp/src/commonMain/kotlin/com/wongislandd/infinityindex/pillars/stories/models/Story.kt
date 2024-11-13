package com.wongislandd.infinityindex.pillars.stories.models

import com.wongislandd.infinityindex.util.DisplayableEntity

data class Story(
    override val id: Int,
    override val displayName: String,
    override val imageUrl: String,
    val type: String?,
    val description: String?
): DisplayableEntity