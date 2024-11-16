package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.infra.navigation.NavigationItem

enum class EntityType(
    val displayName: String,
    val relatedNavigationLink: String,
    val key: String,
    val searchParamType: SearchParamType?
) {
    COMICS(
        "Comics",
        NavigationItem.AllComicListScreen.route,
        "comics",
        SearchParamType.TITLE_STARTS_WITH
    ),
    CHARACTERS(
        "Characters",
        NavigationItem.AllCharacterListScreen.route,
        "characters",
        SearchParamType.NAME_STARTS_WITH
    ),
    CREATORS(
        "Creators",
        NavigationItem.AllCreatorListScreen.route,
        "creators",
        SearchParamType.NAME_STARTS_WITH
    ),
    EVENTS(
        "Events",
        NavigationItem.AllEventListScreen.route,
        "events",
        SearchParamType.NAME_STARTS_WITH
    ),
    SERIES(
        "Series",
        NavigationItem.AllSeriesListScreen.route,
        "series",
        SearchParamType.TITLE_STARTS_WITH
    ),
    STORIES(
        "Stories",
        NavigationItem.AllStoriesListScreen.route,
        "stories",
        null
    ),
}

enum class SearchParamType(val key: String) {
    TITLE_STARTS_WITH("titleStartsWith"),
    NAME_STARTS_WITH("nameStartsWith"),
}