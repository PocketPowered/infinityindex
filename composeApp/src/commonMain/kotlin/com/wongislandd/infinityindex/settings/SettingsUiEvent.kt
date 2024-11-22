package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.repositories.NumberSetting
import com.wongislandd.infinityindex.repositories.ToggleSetting

sealed class SettingsUiEvent : UiEvent {
    data class ToggledSetting(val toggleSetting: ToggleSetting, val shouldEnable: Boolean) : SettingsUiEvent()
    data class NumberSettingChanged(val setting: NumberSetting, val newValue: Int) : SettingsUiEvent()
}