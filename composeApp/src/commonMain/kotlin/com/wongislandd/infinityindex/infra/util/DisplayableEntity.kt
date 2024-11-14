package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.infra.models.LoadableImage

interface DisplayableEntity {
    val id: Int
    val displayName: String
    val image: LoadableImage
}