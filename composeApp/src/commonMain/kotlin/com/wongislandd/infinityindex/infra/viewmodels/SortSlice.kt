package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.infinityindex.infra.ListUiEvent
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.launch

class SortSlice : ViewModelSlice() {

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ListUiEvent.SortSelected -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(PagingBackChannelEvent.SubmitSortSelection(event.sortOption))
                }
            }
        }
    }

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
    }
}