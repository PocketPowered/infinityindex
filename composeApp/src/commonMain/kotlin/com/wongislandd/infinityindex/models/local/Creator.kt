package com.wongislandd.infinityindex.models.local

import com.wongislandd.infinityindex.models.util.RelatedLink
import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.util.EntityModel

data class Creator(
    override val id: Int,
    override val displayName: String,
    override val image: LoadableImage,
    override val navContext: NavigationContext,
    override val relatedComicsCount: Int,
    override val relatedStoriesCount: Int,
    override val relatedCharactersCount: Int,
    override val relatedCreatorsCount: Int,
    override val relatedSeriesCount: Int,
    override val relatedEventsCount: Int,
    override val lastModified: String,
    val relatedLinks: List<RelatedLink>
): EntityModel