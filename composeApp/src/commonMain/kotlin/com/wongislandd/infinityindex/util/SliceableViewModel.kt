package com.wongislandd.infinityindex.util

import androidx.lifecycle.ViewModel
import com.wongislandd.infinityindex.util.events.BackChannelEvent
import com.wongislandd.infinityindex.util.events.EventBus
import com.wongislandd.infinityindex.util.events.UiEvent

abstract class SliceableViewModel(
    val uiEventBus: EventBus<UiEvent>,
    val backChannelEventBus: EventBus<BackChannelEvent>
) : ViewModel() {

    fun registerSlice(viewModelSlice: ViewModelSlice) {
        viewModelSlice.register(this)
    }

    override fun onCleared() {
        super.onCleared()
    }
}