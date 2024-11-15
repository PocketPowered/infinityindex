package com.wongislandd.infinityindex.infra.navigation

sealed class NavigationItem(val route: String) {
    data object ComicListScreen : NavigationItem("comic_list")
    data object ComicDetailsScreen : NavigationItem("comic_details/{comicId}")
    data object CreatorDetailsScreen: NavigationItem("creator_details/{creatorId}")
    data object CharacterDetailsScreen: NavigationItem("character_details/{characterId}")
    data object SeriesDetailsScreen: NavigationItem("series_details/{seriesId}")
    data object EventDetailsScreen: NavigationItem("event_details/{eventId}")
    data object StoryDetailsScreen: NavigationItem("story_details/{storyId}")
}