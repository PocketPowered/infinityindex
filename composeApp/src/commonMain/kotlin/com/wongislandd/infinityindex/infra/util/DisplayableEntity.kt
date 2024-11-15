package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext

interface DisplayableEntity {
    val id: Int
    val displayName: String
    val image: LoadableImage
    val navContext: NavigationContext
}