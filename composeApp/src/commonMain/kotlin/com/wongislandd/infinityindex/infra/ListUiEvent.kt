package com.wongislandd.infinityindex.infra

import com.wongislandd.infinityindex.infra.util.SortOption
import com.wongislandd.infinityindex.infra.util.events.UiEvent

sealed class ListUiEvent : UiEvent {

    object SearchClicked: ListUiEvent()

    data class SortSelected(val sortOption: SortOption): ListUiEvent()

    data class SubmitSearchQuery(val query: String): ListUiEvent()

    data class SetPendingSearchQuery(val query: String): ListUiEvent()
}