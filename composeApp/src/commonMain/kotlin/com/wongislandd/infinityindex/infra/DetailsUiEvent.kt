package com.wongislandd.infinityindex.infra

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.UiEvent

sealed class DetailsUiEvent: UiEvent {
    data class PageInitialized(val comicId: Int, val primaryEntityType: EntityType): DetailsUiEvent()
}