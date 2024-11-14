package com.wongislandd.infinityindex.entities.characters.models

import com.wongislandd.infinityindex.entities.comics.details.models.RelatedLink
import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.util.DisplayableEntity
import com.wongislandd.infinityindex.infra.util.PillarModel

data class Character(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    val description: String?,
    val modified: String?,
    val relatedLinks: List<RelatedLink>,
) : DisplayableEntity, PillarModel