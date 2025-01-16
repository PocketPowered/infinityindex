package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.settings.NumberSetting
import com.wongislandd.infinityindex.settings.ToggleSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class DataStoreRepositoryImpl : DataStoreRepository {
    override suspend fun saveBooleanPreference(setting: ToggleSetting, value: Boolean) {

    }

    override fun readBooleanPreference(setting: ToggleSetting): Flow<Boolean> {
        return flowOf(true)
    }

    override suspend fun saveIntPreference(setting: NumberSetting, value: Int) {
    }

    override fun readIntPreference(setting: NumberSetting): Flow<Int> {
        return flowOf(30)
    }

    override fun isSupported(): Boolean = false
}