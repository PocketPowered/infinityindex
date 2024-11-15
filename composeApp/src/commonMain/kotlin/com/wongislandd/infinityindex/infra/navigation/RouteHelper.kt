package com.wongislandd.infinityindex.infra.navigation

object RouteHelper {
    fun getComicDetailsRouteForId(comicId: Int): String {
        return NavigationItem.ComicDetailsScreen.route.replace("{comicId}", comicId.toString())
    }

    fun getCreatorDetailsRouteForId(creatorId: Int): String {
        return NavigationItem.CreatorDetailsScreen.route.replace("{creatorId}", creatorId.toString())
    }

    fun getCharacterDetailsRouteForId(characterId: Int): String {
        return NavigationItem.CharacterDetailsScreen.route.replace("{characterId}", characterId.toString())
    }

    fun getSeriesDetailsRouteForId(seriesId: Int): String {
        return NavigationItem.SeriesDetailsScreen.route.replace("{seriesId}", seriesId.toString())
    }

    fun getEventDetailsRouteForId(eventId: Int): String {
        return NavigationItem.EventDetailsScreen.route.replace("{eventId}", eventId.toString())
    }

    fun getStoryDetailsRouteForId(storyId: Int): String {
        return NavigationItem.StoryDetailsScreen.route.replace("{storyId}", storyId.toString())
    }
}