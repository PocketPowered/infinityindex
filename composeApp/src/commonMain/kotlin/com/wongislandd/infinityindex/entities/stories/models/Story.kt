package com.wongislandd.infinityindex.entities.stories.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.util.EntityModel

data class Story(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    override val lastModified: String,
    val type: String?,
    val description: String?,
    override val hasComics: Boolean,
    override val hasStories: Boolean,
    override val hasCharacters: Boolean,
    override val hasCreators: Boolean,
    override val hasSeries: Boolean,
    override val hasEvents: Boolean
) : EntityModel