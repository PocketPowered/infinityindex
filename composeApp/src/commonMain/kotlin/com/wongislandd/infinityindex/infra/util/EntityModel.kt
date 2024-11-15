package com.wongislandd.infinityindex.infra.util

/**
 * Check if the entity has any of the following properties, save me
 * a network call if I know it doesn't have related items
 */
interface EntityModel {
    val hasComics: Boolean
    val hasStories: Boolean
    val hasCharacters: Boolean
    val hasCreators: Boolean
    val hasSeries: Boolean
    val hasEvents: Boolean
}