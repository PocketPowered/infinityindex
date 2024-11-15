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
        NavigationItem.ComicListScreen.route,
        "comics",
        SearchParamType.TITLE_STARTS_WITH
    ),
    CHARACTERS(
        "Characters",
        NavigationItem.CharacterListScreen.route,
        "characters",
        SearchParamType.NAME_STARTS_WITH
    ),
    CREATORS(
        "Creators",
        NavigationItem.CreatorListScreen.route,
        "creators",
        SearchParamType.NAME_STARTS_WITH
    ),
    EVENTS(
        "Events",
        NavigationItem.EventListScreen.route,
        "events",
        SearchParamType.NAME_STARTS_WITH
    ),
    SERIES(
        "Series",
        NavigationItem.SeriesListScreen.route,
        "series",
        SearchParamType.TITLE_STARTS_WITH
    ),
    STORIES(
        "Stories",
        NavigationItem.StoriesListScreen.route,
        "stories",
        null
    ),
}

enum class SearchParamType(val key: String) {
    TITLE_STARTS_WITH("titleStartsWith"),
    NAME_STARTS_WITH("nameStartsWith"),
}