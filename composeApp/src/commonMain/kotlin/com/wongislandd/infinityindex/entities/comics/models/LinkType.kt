package com.wongislandd.infinityindex.entities.comics.models

enum class LinkType(
    val displayName: String
) {
    DETAILS("Details"),
    PURCHASE("Purchase"),
    IN_APP_LINK("App Link"),
    READER("Read"),
    UNKNOWN("Unknown")
}