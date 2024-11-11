package com.wongislandd.infinityindex.comics

/**
 * Directly maps to options available via. Marvel comics API
 */
enum class ComicsSortOption(val sortKey: String) {
    FOC_DATE("focDate"),
    ONSALE_DATE("onsaleDate"),
    TITLE("title"),
    ISSUE_NUMBER("issueNumber"),
    MODIFIED("modified")
}