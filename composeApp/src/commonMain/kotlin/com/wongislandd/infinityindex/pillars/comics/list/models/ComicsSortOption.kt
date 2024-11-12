package com.wongislandd.infinityindex.pillars.comics.list.models

/**
 * Directly maps to options available via. Marvel comics API
 */
enum class ComicsSortOption(val displayName: String, val sortKey: String) {
    FOC_DATE_DESC(displayName = "FOC Date (dec)", "-focDate"),
    FOC_DATE_ASC(displayName = "FOC Date (asc)", "focDate"),
    TITLE_DESC(displayName = "Title (dec)", sortKey = "-title"),
    TITLE_ASC(displayName = "Title (asc)", sortKey = "title"),
    MODIFIED_DESC(displayName = "Last Modified (dec)", "-modified"),
    MODIFIED_ASC(displayName = "Last Modified (asc)", "modified"),
    ONSALE_DATE_DESC(displayName = "On Sale Date (dec)", "-onsaleDate"),
    ONSALE_DATE_ASC(displayName = "On Sale Date (asc)", "onsaleDate"),
    ISSUE_NUMBER_DESC(displayName = "Issue Number (dec)", sortKey = "-issueNumber"),
    ISSUE_NUMBER_ASC(displayName = "Issue Number (asc)", sortKey = "issueNumber"),
}