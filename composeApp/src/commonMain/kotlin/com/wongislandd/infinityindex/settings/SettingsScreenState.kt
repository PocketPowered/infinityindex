package com.wongislandd.infinityindex.settings

data class SettingsScreenState(
    val toggleSettings: List<SelectableToggleSetting>,
    val numberSettings: List<AdjustableNumberSetting>
)