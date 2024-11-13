package com.wongislandd.infinityindex.entities.characters.models

import com.wongislandd.infinityindex.entities.comics.details.models.RelatedLink
import com.wongislandd.infinityindex.util.DisplayableEntity

data class Character(
    override val id: Int,
    override val displayName: String,
    override val imageUrl: String,
    val description: String?,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
) : DisplayableEntity