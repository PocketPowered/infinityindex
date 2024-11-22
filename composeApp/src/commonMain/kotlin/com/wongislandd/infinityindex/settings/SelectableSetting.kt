package com.wongislandd.infinityindex.settings

import com.wongislandd.infinityindex.repositories.Setting

data class SelectableSetting(
    val setting: Setting,
    val isSettingEnabled: Boolean,
)