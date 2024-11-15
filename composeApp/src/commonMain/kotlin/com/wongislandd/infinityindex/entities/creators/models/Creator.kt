package com.wongislandd.infinityindex.entities.creators.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.util.DisplayableEntity
import com.wongislandd.infinityindex.infra.util.PillarModel

data class Creator(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext
): DisplayableEntity, PillarModel