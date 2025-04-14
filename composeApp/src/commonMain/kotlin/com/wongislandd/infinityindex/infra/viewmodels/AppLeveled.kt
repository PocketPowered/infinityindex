package com.wongislandd.infinityindex.infra.viewmodels

import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal const val DATA_STORE_FILE_NAME = "prefs.preferences_pb"

@OptIn(InternalAPI::class)
object AppLeveled {

    // As a workaround to caching requests with the timestamp being a parameter to the request, I'll
    // maintain the same timestamp for a fixed period of time so requests can be repeated.
    // Cache variable to hold the last timestamp and when it was last updated. Not ideal.
    private var lastTimestamp: Instant? = null
    private const val STALE_TIME_MINUTES = 5L // Set the stale time to 5 minutes

    private val _attributionText: MutableStateFlow<String?> = MutableStateFlow(null)
    val attributionText: StateFlow<String?> = _attributionText

    // Function to get a fresh timestamp, checking if the previous one is stale
    fun getFreshTimestamp(): Instant {
        val currentTime = Clock.System.now()
        return lastTimestamp?.also {
            if (currentTime.epochSeconds - it.epochSeconds >= STALE_TIME_MINUTES * 60) {
                lastTimestamp = currentTime
            }
        } ?: run {
            lastTimestamp = currentTime
            return currentTime
        }
    }

    fun updateAttributionText(attributionText: String?) {
        _attributionText.update { attributionText }
    }
}