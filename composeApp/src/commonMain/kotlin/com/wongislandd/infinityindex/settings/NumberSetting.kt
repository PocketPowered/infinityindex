package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.ComicConstants

enum class NumberSetting(
    override val displayName: String,
    override val description: String,
    override val key: String,
    override val defaultValue: Int,
) : Setting<Int> {
    LOOK_AHEAD_DAYS(
        "Look Forward Days",
        "Comics served will be available at most this many days in the future.",
        "numberOfComicsPerPage",
        ComicConstants.DEFAULT_LOOK_AHEAD_DAYS
    )
}