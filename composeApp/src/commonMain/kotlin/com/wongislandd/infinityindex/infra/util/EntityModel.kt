package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext

/**
 * Check if the entity has any of the following properties, save me
 * a network call if I know it doesn't have related items
 */
interface EntityModel: DisplayableEntity {
    override val id: Int
    override val displayName: String
    override val image: LoadableImage
    override val navContext: NavigationContext
    val lastModified: String
    val hasComics: Boolean
    val hasStories: Boolean
    val hasCharacters: Boolean
    val hasCreators: Boolean
    val hasSeries: Boolean
    val hasEvents: Boolean
}