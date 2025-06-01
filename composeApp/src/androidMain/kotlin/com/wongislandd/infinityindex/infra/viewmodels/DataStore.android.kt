package com.wongislandd.infinityindex.infra.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.wongislandd.infinityindex.settings.NumberSetting
import com.wongislandd.infinityindex.settings.ToggleSetting
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import java.io.File

object DatastoreProvider {
    private lateinit var dataStore: DataStore<Preferences>
    private val lock = SynchronizedObject()

    fun getDatastore(context: Context, fileName: String): DataStore<Preferences> {
        return synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                val path = File(context.filesDir, fileName).absolutePath.toPath()
                PreferenceDataStoreFactory.createWithPath {
                   path
                }.also { dataStore = it }
            }
        }
    }
}


actual class DataStoreRepositoryImpl actual constructor(context: Any?) : DataStoreRepository {

    private val dataStore = DatastoreProvider.getDatastore(context as Context, DATA_STORE_FILE_NAME)

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