package com.wongislandd.infinityindex.entities.series.models

import com.wongislandd.infinityindex.util.DisplayableEntity

data class Series(
    override val id: Int,
    override val displayName: String,
    override val imageUrl: String,
    val description: String?,
    val rating: String?,
    val startYear: Int?,
    val endYear: Int?
): DisplayableEntity