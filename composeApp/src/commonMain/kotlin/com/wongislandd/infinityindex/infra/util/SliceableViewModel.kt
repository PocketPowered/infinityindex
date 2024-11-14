package com.wongislandd.infinityindex.infra.util

import androidx.lifecycle.ViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseScreenStateSlice

abstract class SliceableViewModel<model : PillarModel>(
    val entityType: EntityType,
    val screenStateSlice: BaseScreenStateSlice<model>,
    val uiEventBus: EventBus<UiEvent>,
    val backChannelEventBus: EventBus<BackChannelEvent>
) : ViewModel() {

    fun registerSlice(viewModelSlice: ViewModelSlice<*>) {
        viewModelSlice.register(this)
    }

    override fun onCleared() {
        super.onCleared()
    }
}