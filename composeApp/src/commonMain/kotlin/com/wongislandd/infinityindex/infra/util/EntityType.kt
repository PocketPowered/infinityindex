package com.wongislandd.infinityindex.infra.util

import NavigationItem

enum class EntityType(
    val displayName: String,
    val viewAllNavigationLink: String,
    val viewRelatedNavigationLink: (primaryEntityId: Int, primaryEntityType: EntityType, topBarTitle: String) -> String,
    val key: String,
    val searchParamType: SearchParamType?
) {
    COMICS(
        "Comics",
        NavigationItem.AllComicListScreen.route,
        { primaryEntityId, primaryEntityType, topBarTitle ->
            val targetScreen = NavigationItem.RelatedComicListScreen
            targetScreen.constructRoute(
                mapOf(
                    targetScreen.idArg to primaryEntityType.name,
                    targetScreen.idArg2 to primaryEntityId.toString(),
                    targetScreen.idArg3 to COMICS.name,
                    targetScreen.idArg4 to topBarTitle
                )
            )
        },
        "comics",
        SearchParamType.TITLE_STARTS_WITH
    ),
    CHARACTERS(
        "Characters",
        NavigationItem.AllCharacterListScreen.route,
        { primaryEntityId, primaryEntityType, topBarTitle ->
            val targetScreen = NavigationItem.RelatedCharactersListScreen
            targetScreen.constructRoute(
                mapOf(
                    targetScreen.idArg to primaryEntityType.name,
                    targetScreen.idArg2 to primaryEntityId.toString(),
                    targetScreen.idArg3 to CHARACTERS.name,
                    targetScreen.idArg4 to topBarTitle
                )
            )
        },
        "characters",
        SearchParamType.NAME_STARTS_WITH
    ),
    CREATORS(
        "Creators",
        NavigationItem.AllCreatorListScreen.route,
        { primaryEntityId, primaryEntityType, topBarTitle ->
            val targetScreen = NavigationItem.RelatedCreatorsListScreen
            targetScreen.constructRoute(
                mapOf(
                    targetScreen.idArg to primaryEntityType.name,
                    targetScreen.idArg2 to primaryEntityId.toString(),
                    targetScreen.idArg3 to CREATORS.name,
                    targetScreen.idArg4 to topBarTitle
                )
            )
        },
        "creators",
        SearchParamType.NAME_STARTS_WITH
    ),
    EVENTS(
        "Events",
        NavigationItem.AllEventListScreen.route,
        { primaryEntityId, primaryEntityType, topBarTitle ->
            val targetScreen = NavigationItem.RelatedComicListScreen
            targetScreen.constructRoute(
                mapOf(
                    targetScreen.idArg to primaryEntityType.name,
                    targetScreen.idArg2 to primaryEntityId.toString(),
                    targetScreen.idArg3 to EVENTS.name,
                    targetScreen.idArg4 to topBarTitle
                )
            )
        },
        "events",
        SearchParamType.NAME_STARTS_WITH
    ),
    SERIES(
        "Series",
        NavigationItem.AllSeriesListScreen.route,
        { primaryEntityId, primaryEntityType, topBarTitle ->
            val targetScreen = NavigationItem.RelatedSeriesListScreen
            targetScreen.constructRoute(
                mapOf(
                    targetScreen.idArg to primaryEntityType.name,
                    targetScreen.idArg2 to primaryEntityId.toString(),
                    targetScreen.idArg3 to SERIES.name,
                    targetScreen.idArg4 to topBarTitle
                )
            )
        },
        "series",
        SearchParamType.TITLE_STARTS_WITH
    ),
    STORIES(
        "Stories",
        NavigationItem.AllStoriesListScreen.route,
        { primaryEntityId, primaryEntityType, topBarTitle ->
            val targetScreen = NavigationItem.RelatedStoriesListScreen
            targetScreen.constructRoute(
                mapOf(
                    targetScreen.idArg to primaryEntityType.name,
                    targetScreen.idArg2 to primaryEntityId.toString(),
                    targetScreen.idArg3 to STORIES.name,
                    targetScreen.idArg4 to topBarTitle
                )
            )
        },
        "stories",
        null
    ),
}

enum class SearchParamType(val key: String) {
    TITLE_STARTS_WITH("titleStartsWith"),
    NAME_STARTS_WITH("nameStartsWith"),
}