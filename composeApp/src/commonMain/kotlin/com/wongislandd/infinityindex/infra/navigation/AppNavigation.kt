package com.wongislandd.infinityindex.infra.navigation

enum class NavigationItem(
    private val baseRoute: String,
    val idArg: String? = null, ) {
    ComicListScreen("comics"),
    CreatorListScreen("creators"),
    CharacterListScreen("characters"),
    SeriesListScreen("series"),
    EventListScreen("events"),
    StoryListScreen("stories"),
    ComicDetailsScreen("comics/details", "comicId"),
    CreatorDetailsScreen("creators/details", "creatorId"),
    CharacterDetailsScreen("character/details", "characterId"),
    SeriesDetailsScreen("series/details", "seriesId"),
    EventDetailsScreen("event/details", "eventId"),
    StoryDetailsScreen("story/details", "storyId");

    val route: String
        get() = if (idArg != null) "$baseRoute/{$idArg}" else baseRoute
}