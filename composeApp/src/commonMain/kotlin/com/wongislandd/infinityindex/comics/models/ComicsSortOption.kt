package com.wongislandd.infinityindex.comics.models

/**
 * Directly maps to options available via. Marvel comics API
 */
enum class ComicsSortOption(val displayName: String, val sortKey: String? = null) {
    NONE(displayName = "Default"),
    TITLE_ASC(displayName = "Title (asc)", sortKey = "title"),
    TITLE_DESC(displayName = "Title (dec)", sortKey = "-title"),
    MODIFIED_ASC(displayName = "Last Modified (asc)", "modified"),
    MODIFIED_DESC(displayName = "Last Modified (dec)", "-modified"),
    ONSALE_DATE_ASC(displayName = "On Sale Date (asc)", "onsaleDate"),
    ONSALE_DATE_DESC(displayName = "On Sale Date (dec)", "-onsaleDate"),
    ISSUE_NUMBER_ASC(displayName = "Issue Number (asc)", sortKey = "issueNumber"),
    ISSUE_NUMBER_DESC(displayName = "Issue Number (dec)", sortKey = "-issueNumber"),
    FOC_DATE_ASC(displayName = "FOC Date (asc)", "focDate"),
    FOC_DATE_DESC(displayName = "FOC Date (dec)", "-focDate"),
}