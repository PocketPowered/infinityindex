package com.wongislandd.infinityindex.infra.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.wongislandd.infinityindex.settings.NumberSetting
import com.wongislandd.infinityindex.settings.ToggleSetting
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

object DatastoreProvider {
    private lateinit var dataStore: DataStore<Preferences>
    private val lock = SynchronizedObject()

    fun getDatastore(producePath: () -> String): DataStore<Preferences> {
        return synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath {
                    producePath().toPath()
                }.also { dataStore = it }
            }
        }
    }
}

actual class DataStoreRepositoryImpl actual constructor(context: Any?) : DataStoreRepository {

    @OptIn(ExperimentalForeignApi::class)
    private val dataStore = DatastoreProvider.getDatastore {
        val directory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        requireNotNull(directory).path + "/$DATA_STORE_FILE_NAME"
    }

    override suspend fun saveBooleanPreference(setting: ToggleSetting, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(setting.key)] = value
        }
    }

    override fun readBooleanPreference(setting: ToggleSetting): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(setting.key)] ?: setting.defaultValue
        }
    }

    override suspend fun saveIntPreference(setting: NumberSetting, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(setting.key)] = value
        }
    }

    override fun readIntPreference(setting: NumberSetting): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[intPreferencesKey(setting.key)] ?: setting.defaultValue
        }
    }

    override fun isSupported(): Boolean = true
}