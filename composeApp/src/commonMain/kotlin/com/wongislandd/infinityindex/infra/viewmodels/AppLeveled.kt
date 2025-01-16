package com.wongislandd.infinityindex.infra.viewmodels

import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal const val DATA_STORE_FILE_NAME = "prefs.preferences_pb"

@OptIn(InternalAPI::class)
object AppLeveled {

    private val _attributionText: MutableStateFlow<String?> = MutableStateFlow(null)
    val attributionText: StateFlow<String?> = _attributionText

    fun updateAttributionText(attributionText: String?) {
        _attributionText.update { attributionText }
    }
}