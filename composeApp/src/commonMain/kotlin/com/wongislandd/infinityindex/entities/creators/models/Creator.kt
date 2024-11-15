package com.wongislandd.infinityindex.entities.creators.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.util.EntityModel

data class Creator(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    override val hasComics: Boolean,
    override val hasStories: Boolean,
    override val hasCharacters: Boolean,
    override val hasCreators: Boolean,
    override val hasSeries: Boolean,
    override val hasEvents: Boolean,
    override val lastModified: String
): EntityModel