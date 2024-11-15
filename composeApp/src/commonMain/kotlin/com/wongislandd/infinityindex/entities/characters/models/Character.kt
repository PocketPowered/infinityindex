package com.wongislandd.infinityindex.entities.characters.models

import com.wongislandd.infinityindex.entities.comics.details.models.RelatedLink
import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.util.EntityModel

data class Character(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    val description: String?,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
    override val hasComics: Boolean,
    override val hasStories: Boolean,
    override val hasCharacters: Boolean,
    override val hasCreators: Boolean,
    override val hasSeries: Boolean,
    override val hasEvents: Boolean,
    override val lastModified: String,
) : EntityModel