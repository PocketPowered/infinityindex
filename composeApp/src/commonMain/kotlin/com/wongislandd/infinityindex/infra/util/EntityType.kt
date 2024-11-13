package com.wongislandd.infinityindex.infra.util

enum class EntityType(val key: String, val searchParamType: SearchParamType?) {
    COMICS("comics", SearchParamType.TITLE_STARTS_WITH),
    CHARACTERS("characters", SearchParamType.NAME_STARTS_WITH),
    CREATORS("creators", SearchParamType.NAME_STARTS_WITH),
    COMIC_EVENTS("events", SearchParamType.NAME_STARTS_WITH),
    SERIES("series", SearchParamType.TITLE_STARTS_WITH),
    STORIES("stories", null),
}

enum class SearchParamType(val key: String) {
    TITLE_STARTS_WITH("titleStartsWith"),
    NAME_STARTS_WITH("nameStartsWith"),
}