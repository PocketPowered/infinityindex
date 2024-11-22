package com.wongislandd.infinityindex.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

enum class Setting(
    val displayName: String,
    val description: String,
    val key: Preferences.Key<Boolean>
) {
    VARIANTS(
        "Display Variants",
        "Variants are copies of a comic, with different cover art.",
        booleanPreferencesKey("variants")
    ),
    DIGITALLY_AVAILABLE(
        "Limit Results to Digitally Available",
        "Enabling this will limit comic results to those which are available to read online.",
        booleanPreferencesKey("digitallyAvailable")
    ),
    DARK_MODE(
        "Dark Mode",
        "Force the app into dark mode (regardless of system settings)",
        booleanPreferencesKey("darkMode")
    )
}

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveBooleanPreference(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun readBooleanPreference(key: Preferences.Key<Boolean>): Boolean {
        return dataStore.data.map { preferences ->
            preferences[key]
        }.first() ?: false
    }
}