package com.wongislandd.infinityindex.infra.navigation

enum class NavigationItem(
    val displayName: String,
    private val baseRoute: String,
    val idArg: String? = null,
) {
    StartUp("Start Up", "startUp"),
    Home("Home", "home"),
    ComicListScreen("Comics", "comics"),
    CreatorListScreen("Creators", "creators"),
    CharacterListScreen("Characters", "characters"),
    SeriesListScreen("Series", "series"),
    EventListScreen("Events", "events"),
    StoriesListScreen("Stories", "stories"),
    ComicDetailsScreen("Comic Details", "comics/details", "comicId"),
    CreatorDetailsScreen("Creator Details", "creators/details", "creatorId"),
    CharacterDetailsScreen("Character Details", "character/details", "characterId"),
    SeriesDetailsScreen("Series Details", "series/details", "seriesId"),
    EventDetailsScreen("Event Details", "event/details", "eventId"),
    StoryDetailsScreen("Story Details", "story/details", "storyId");

    val route: String
        get() = if (idArg != null) "$baseRoute/{$idArg}" else baseRoute
}