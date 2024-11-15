package com.wongislandd.infinityindex.infra.navigation

enum class NavigationItem(
    private val baseRoute: String,
    val idArg: String? = null, ) {
    ComicListScreen("comic_list"),
    ComicDetailsScreen("comic_details", "comicId"),
    CreatorDetailsScreen("creator_details", "creatorId"),
    CharacterDetailsScreen("character_details", "characterId"),
    SeriesDetailsScreen("series_details", "seriesId"),
    EventDetailsScreen("event_details", "eventId"),
    StoryDetailsScreen("story_details", "storyId");

    val route: String
        get() = if (idArg != null) "$baseRoute/{$idArg}" else baseRoute
}