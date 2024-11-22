package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.repositories.Setting

sealed class SettingsUiEvent : UiEvent {
    data class AdjustSetting(val setting: Setting, val shouldEnable: Boolean) : SettingsUiEvent()
}