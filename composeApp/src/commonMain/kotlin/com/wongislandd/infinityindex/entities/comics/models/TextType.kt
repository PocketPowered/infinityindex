package com.wongislandd.infinityindex.entities.comics.models

enum class TextType(
    val displayName: String
) {
    ISSUE_SOLICIT_TEXT("Issue Solicit"),
    PREVIEW_TEXT("Preview"),
    ISSUE_PREVIEW_TEXT("Issue Preview Text"),
    UNKNOWN("Unknown")
}