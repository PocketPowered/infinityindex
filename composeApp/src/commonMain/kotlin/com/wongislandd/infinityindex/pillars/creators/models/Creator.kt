package com.wongislandd.infinityindex.pillars.creators.models

import com.wongislandd.infinityindex.util.DisplayableEntity

data class Creator(
    override val id: Int,
    override val displayName: String,
    override val imageUrl: String
): DisplayableEntity