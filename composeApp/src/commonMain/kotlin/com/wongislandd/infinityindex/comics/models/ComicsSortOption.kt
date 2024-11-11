package com.wongislandd.infinityindex.comics.models

/**
 * Directly maps to options available via. Marvel comics API
 */
enum class ComicsSortOption(val displayName: String, val sortKey: String? = null) {
    NONE(displayName = "Default"),
    MODIFIED(displayName = "Last Modified", "modified"),
    ONSALE_DATE(displayName = "On Sale Date", "onsaleDate"),
    ISSUE_NUMBER(displayName = "Issue Number", sortKey = "issueNumber"),
    TITLE(displayName = "Title", sortKey = "title"),
    FOC_DATE(displayName = "FOC Date", "focDate"),
}