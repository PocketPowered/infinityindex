package com.wongislandd.infinityindex.infra.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

object AppLeveled {

    private val _attributionText: MutableStateFlow<String?> = MutableStateFlow(null)
    val attributionText: StateFlow<String?> = _attributionText

    fun updateAttributionText(attributionText: String?) {
        _attributionText.update { attributionText }
    }
}