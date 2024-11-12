package com.wongislandd.infinityindex.navigation

object RouteHelper {
    fun getComicDetailsRouteForId(comicId: Int): String {
        return NavigationItem.ComicDetails.route.replace("{comicId}", comicId.toString())
    }
}