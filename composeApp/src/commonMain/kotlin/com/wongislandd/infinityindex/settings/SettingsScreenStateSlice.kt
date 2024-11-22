package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.repositories.DataStoreRepository
import com.wongislandd.infinityindex.repositories.Setting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenStateSlice(
    private val dataStoreRepository: DataStoreRepository
) : ViewModelSlice() {

    private val _screenState: MutableStateFlow<SettingsScreenState> =
        MutableStateFlow(SettingsScreenState(emptyList()))

    val screenState: StateFlow<SettingsScreenState> = _screenState

    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            val settings = Setting.entries.map {
                val isSettingEnabled = dataStoreRepository.readBooleanPreference(it.key)
                SelectableSetting(
                    it,
                    isSettingEnabled = isSettingEnabled
                )
            }
            _screenState.update {
                it.copy(settings = settings)
            }
        }
    }

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is SettingsUiEvent.AdjustSetting -> {
                adjustSetting(event.setting, event.shouldEnable)
            }
        }

    }

    private fun adjustSetting(setting: Setting, shouldEnable: Boolean) {
        sliceScope.launch {
            dataStoreRepository.saveBooleanPreference(setting.key, shouldEnable)
            _screenState.update {
                val newList = it.settings.map { existingSetting ->
                    if (existingSetting.setting == setting) {
                        existingSetting.copy(isSettingEnabled = shouldEnable)
                    } else {
                        existingSetting
                    }
                }
                it.copy(settings = newList)
            }
        }
    }
}