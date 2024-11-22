package com.wongislandd.infinityindex.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val variantsPreferencesKey = booleanPreferencesKey("variants")
val digitallyAvailablePreferencesKey = booleanPreferencesKey("digitallyAvailable")

enum class FilterType(val key: Preferences.Key<Boolean>) {
    VARIANTS(variantsPreferencesKey),
    DIGITALLY_AVAILABLE(digitallyAvailablePreferencesKey)
}

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveFilterPreference(filterType: FilterType, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[filterType.key] = value
        }
    }

    suspend fun readFilterPreference(filterType: FilterType): Boolean {
        return dataStore.data.map { preferences ->
            preferences[filterType.key]
        }.first() ?: false
     }
}