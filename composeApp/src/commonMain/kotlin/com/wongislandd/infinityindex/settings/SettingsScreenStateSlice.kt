package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.repositories.DataStoreRepository
import com.wongislandd.infinityindex.repositories.NumberSetting
import com.wongislandd.infinityindex.repositories.ToggleSetting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenStateSlice(
    private val dataStoreRepository: DataStoreRepository
) : ViewModelSlice() {

    private val _screenState: MutableStateFlow<SettingsScreenState> =
        MutableStateFlow(SettingsScreenState(emptyList(), emptyList()))

    val screenState: StateFlow<SettingsScreenState> = _screenState

    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            val toggleSettings = ToggleSetting.entries.map {
                val isSettingEnabled = dataStoreRepository.readBooleanPreference(it)
                SelectableToggleSetting(
                    it,
                    currentValue = isSettingEnabled
                )
            }
            val numberSettings = NumberSetting.entries.map {
                val currentValue = dataStoreRepository.readIntPreference(it)
                AdjustableNumberSetting(
                    it,
                    currentValue = currentValue
                )
            }
            _screenState.update {
                it.copy(
                    toggleSettings = toggleSettings,
                    numberSettings = numberSettings
                )
            }
        }
    }

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is SettingsUiEvent.ToggledSetting -> {
                adjustToggleSetting(event.toggleSetting, event.shouldEnable)
            }
            is SettingsUiEvent.NumberSettingChanged -> {
                adjustNumberSetting(event.setting, event.newValue)
            }

        }
    }

    private fun adjustToggleSetting(toggleSetting: ToggleSetting, shouldEnable: Boolean) {
        sliceScope.launch {
            dataStoreRepository.saveBooleanPreference(toggleSetting, shouldEnable)
            _screenState.update {
                val newList = it.toggleSettings.map { existingSetting ->
                    if (existingSetting.setting == toggleSetting) {
                        existingSetting.copy(currentValue = shouldEnable)
                    } else {
                        existingSetting
                    }
                }
                it.copy(toggleSettings = newList)
            }
        }
    }

    private fun adjustNumberSetting(setting: NumberSetting, newValue: Int) {
        sliceScope.launch {
            dataStoreRepository.saveIntPreference(setting, newValue)
            _screenState.update {
                val newList = it.numberSettings.map { existingSetting ->
                    if (existingSetting.setting == setting) {
                        existingSetting.copy(currentValue = newValue)
                    } else {
                        existingSetting
                    }
                }
                it.copy(numberSettings = newList)
            }
        }
    }
}