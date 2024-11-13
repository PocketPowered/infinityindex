package com.wongislandd.infinityindex.entities.creators.models

import com.wongislandd.infinityindex.networking.util.SortOption

enum class CreatorsSortOption(
    override val displayName: String,
    override val sortKey: String,
    override val isDefault: Boolean = false
) : SortOption {
    FIRST_NAME_ASC("First Name (A-Z)", "firstName", isDefault = true),
    FIRST_NAME_DEC("First Name (Z-A)", "-firstName"),
    LAST_NAME_ASC("Last Name (A-Z)", "lastName"),
    LAST_NAME_DEC("Last Name (Z-A)", "-lastName"),
    MIDDLE_NAME_ASC("Middle Name (A-Z)", "middleName"),
    MIDDLE_NAME_DEC("Middle Name (Z-A)", "-middleName"),
}