enum class NavigationItem(
    val displayName: String,
    private val baseRoute: String,
    val idArg: String? = null,
    val idArg2: String? = null,
    val idArg3: String? = null,
    val idArg4: String? = null
) {
    StartUp("Start Up", "startUp"),
    Home("Home", "home"),
    RelatedComicListScreen("Related", "comics/related", "rootEntity", "rootEntityId", "relatedEntity", "title"),
    RelatedCharactersListScreen("Related", "characters/related", "rootEntity", "rootEntityId", "relatedEntity", "title"),
    RelatedCreatorsListScreen("Related", "creators/related", "rootEntity", "rootEntityId", "relatedEntity", "title"),
    RelatedSeriesListScreen("Related", "series/related", "rootEntity", "rootEntityId", "relatedEntity", "title"),
    RelatedEventsListScreen("Related", "events/related", "rootEntity", "rootEntityId", "relatedEntity", "title"),
    RelatedStoriesListScreen("Related", "stories/related", "rootEntity", "rootEntityId", "relatedEntity", "title"),
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
            val args = listOfNotNull(idArg, idArg2, idArg3, idArg4).joinToString("/") { "{$it}" }
            return if (args.isNotEmpty()) "$baseRoute/$args" else baseRoute
        }

    /**
     * Reconstructs the route dynamically with provided arguments.
     *
     * @param args A map containing argument keys and their respective values.
     * @return The reconstructed route string.
     * @throws IllegalArgumentException If required arguments are missing.
     */
    fun constructRoute(args: Map<String?, String?>): String {
        val providedArgs = listOfNotNull(idArg, idArg2, idArg3, idArg4).map { argKey ->
            args[argKey] ?: throw IllegalArgumentException("Missing argument: $argKey for $name")
        }
        return if (providedArgs.isNotEmpty()) "$baseRoute/${providedArgs.joinToString("/")}" else baseRoute
    }
}