package com.wongislandd.infinityindex.entities.comics.models

enum class LinkType(
    val displayName: String
) {
    DETAILS("Details"),
    PURCHASE("Purchase"),
    IN_APP_LINK("App Link"),
    WIKI("Wiki"),
    COMIC_LINK("Comic"),
    READER("Read"),
    UNKNOWN("Unknown")
}