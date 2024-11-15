package com.wongislandd.infinityindex.entities.series.models

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.util.DisplayableEntity
import com.wongislandd.infinityindex.infra.util.PillarModel

data class Series(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    val description: String?,
    val rating: String?,
    val startYear: Int?,
    val endYear: Int?
): DisplayableEntity, PillarModel