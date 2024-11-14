package com.wongislandd.infinityindex.entities.stories.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.util.DisplayableEntity

data class Story(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    val type: String?,
    val description: String?
) : DisplayableEntity