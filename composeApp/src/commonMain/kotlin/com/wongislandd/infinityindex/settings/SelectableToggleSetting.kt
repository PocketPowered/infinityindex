package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.repositories.NumberSetting
import com.wongislandd.infinityindex.repositories.ToggleSetting

data class SelectableToggleSetting(
    val setting: ToggleSetting,
    val currentValue: Boolean,
)

data class AdjustableNumberSetting(
    val setting: NumberSetting,
    val currentValue: Int,
)