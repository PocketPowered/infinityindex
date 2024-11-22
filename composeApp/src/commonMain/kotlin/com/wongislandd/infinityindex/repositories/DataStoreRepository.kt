package com.wongislandd.infinityindex.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.wongislandd.infinityindex.settings.NumberSetting
import com.wongislandd.infinityindex.settings.ToggleSetting
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveBooleanPreference(setting: ToggleSetting, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[setting.key] = value
        }
    }

    suspend fun readBooleanPreference(setting: ToggleSetting): Boolean {
        return dataStore.data.map { preferences ->
            preferences[setting.key]
        }.first() ?: false
    }

    suspend fun saveIntPreference(setting: NumberSetting, value: Int) {
        dataStore.edit { preferences ->
            preferences[setting.key] = value
        }
    }

    suspend fun readIntPreference(setting: NumberSetting): Int {
        return dataStore.data.map { preferences ->
            preferences[setting.key]
        }.first() ?: setting.defaultValue
    }
}