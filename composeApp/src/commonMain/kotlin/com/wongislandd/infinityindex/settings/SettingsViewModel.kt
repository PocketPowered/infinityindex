package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

class SettingsViewModel(
    val settingsScreenStateSlice: SettingsScreenStateSlice,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        registerSlice(settingsScreenStateSlice)
    }
}