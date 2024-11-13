package com.wongislandd.infinityindex.pillars.characters.models

import com.wongislandd.infinityindex.pillars.comics.details.models.RelatedLink
import com.wongislandd.infinityindex.util.DisplayableEntity

data class Character(
    override val id: Int,
    override val displayName: String,
    override val imageUrl: String,
    val description: String?,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
) : DisplayableEntity