package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.ListBackChannelEvent
import com.wongislandd.infinityindex.infra.ListUiEvent
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.launch

class SearchSlice : ViewModelSlice() {

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ListUiEvent.SearchClicked -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(ListBackChannelEvent.UpdateSearchBoxVisibility(true))
                }
            }
            is ListUiEvent.SetPendingSearchQuery -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(ListBackChannelEvent.UpdatePendingSearchQuery(event.query))
                }
            }
            is ListUiEvent.SubmitSearchQuery -> {
                sliceScope.launch {
                    backChannelEvents.sendEvent(ListBackChannelEvent.SubmitSearchQuery(event.query))
                }
            }
        }

    }
}