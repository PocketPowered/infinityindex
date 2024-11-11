package com.wongislandd.infinityindex.navigation

enum class Screen {
    HOME,
    COMIC_LIST,
    COMIC_DETAILS
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem("home")
    data object ComicList : NavigationItem("comic_list")
    data object ComicDetails : NavigationItem("comic_details/{comicId}")
}