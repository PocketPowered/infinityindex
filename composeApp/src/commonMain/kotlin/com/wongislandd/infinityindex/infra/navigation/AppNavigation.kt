package com.wongislandd.infinityindex.infra.navigation

sealed class NavigationItem(val route: String) {
    data object ComicList : NavigationItem("comic_list")
    data object ComicDetails : NavigationItem("comic_details/{comicId}")
    data object CreatorDetails: NavigationItem("creator_details/{creatorId}")
    data object CharacterDetails: NavigationItem("character_details/{characterId}")
    data object SeriesDetails: NavigationItem("series_details/{seriesId}")
    data object EventDetails: NavigationItem("event_details/{eventId}")
    data object StoryDetails: NavigationItem("story_details/{storyId}")
}