enum class NavigationItem(
    val displayName: String,
    private val baseRoute: String,
    val idArg: String? = null,
    val idArg2: String? = null,
    val idArg3: String? = null,
) {
    StartUp("Start Up", "startUp"),
    Home("Home", "home"),
    RelatedListScreen("Related", "related", "rootEntity", "rootEntityId", "relatedEntity"),
    AllComicListScreen("All Comics", "comics"),
    AllCreatorListScreen("All Creators", "creators"),
    AllCharacterListScreen("All Characters", "characters"),
    AllSeriesListScreen("All Series", "series"),
    AllEventListScreen("All Events", "events"),
    AllStoriesListScreen("All Stories", "stories"),
    ComicDetailsScreen("Comic Details", "comics/details", "comicId"),
    CreatorDetailsScreen("Creator Details", "creators/details", "creatorId"),
    CharacterDetailsScreen("Character Details", "character/details", "characterId"),
    SeriesDetailsScreen("Series Details", "series/details", "seriesId"),
    EventDetailsScreen("Event Details", "event/details", "eventId"),
    StoryDetailsScreen("Story Details", "story/details", "storyId");

    val route: String
        get() {
            val args = listOfNotNull(idArg, idArg2, idArg3).joinToString("/") { "{$it}" }
            return if (args.isNotEmpty()) "$baseRoute/$args" else baseRoute
        }
}