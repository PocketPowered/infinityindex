package com.wongislandd.infinityindex.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.wongislandd.infinityindex.ComicConstants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

interface Setting<T : Any> {
    val displayName: String
    val description: String
    val key: Preferences.Key<T>
    val defaultValue: T
}

enum class ToggleSetting(
    override val displayName: String,
    override val description: String,
    override val key: Preferences.Key<Boolean>,
    override val defaultValue: Boolean
) : Setting<Boolean> {
    VARIANTS(
        "Display Variants",
        "Variants are copies of a comic, with different cover art.",
        booleanPreferencesKey("variants"),
        false
    ),
    DIGITALLY_AVAILABLE(
        "Limit Results to Digitally Available",
        "Enabling this will limit comic results to those which are available to read online.",
        booleanPreferencesKey("digitallyAvailable"),
        false
    ),
    DARK_MODE(
        "Dark Mode",
        "Force the app into dark mode (regardless of system settings)",
        booleanPreferencesKey("darkMode"),
        false
    )
}

enum class NumberSetting(
    override val displayName: String,
    override val description: String,
    override val key: Preferences.Key<Int>,
    override val defaultValue: Int,
) : Setting<Int> {
    LOOK_AHEAD_DAYS(
        "Look Forward Days",
        "Comics served will be available at most this many days in the future.",
        intPreferencesKey("numberOfComicsPerPage"),
        ComicConstants.DEFAULT_LOOK_AHEAD_DAYS
    )
}

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