package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.settings.NumberSetting
import com.wongislandd.infinityindex.settings.ToggleSetting
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveBooleanPreference(setting: ToggleSetting, value: Boolean)

    fun readBooleanPreference(setting: ToggleSetting): Flow<Boolean>

    suspend fun saveIntPreference(setting: NumberSetting, value: Int)

    fun readIntPreference(setting: NumberSetting): Flow<Int>

    fun isSupported(): Boolean
}

expect class DataStoreRepositoryImpl(context: Any? = null): DataStoreRepository