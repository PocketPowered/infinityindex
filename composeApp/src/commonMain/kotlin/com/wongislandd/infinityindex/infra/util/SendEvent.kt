package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.sendEvent(eventBus: EventBus<UiEvent>, event: UiEvent) {
    launch {
        eventBus.sendEvent(event)
    }
}
