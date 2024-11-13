package com.wongislandd.infinityindex.infra.util.events

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.module.Module

class EventBus<T: Event> {

    // Private mutable SharedFlow for emitting events
    private val _events = MutableSharedFlow<T>(replay = 0)
    val events: SharedFlow<T> = _events.asSharedFlow()

    // Function to emit events, should be called from the UI
    suspend fun sendEvent(event: T) {
        _events.emit(event)
    }
}

// Extension function to start collecting events in a CoroutineScope
fun <T: Event> CoroutineScope.collectEvents(eventBus: EventBus<T>, onEvent: (T) -> Unit) {
    this.launch {
        eventBus.events.collect { event ->
            onEvent(event)
        }
    }
}

// Inline function to register a generic EventBus factory
inline fun <reified T : Event> Module.eventBusFactory() {
    factory { EventBus<Event>() }
}