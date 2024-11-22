package com.wongislandd.infinityindex.infra.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import android.content.Context

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    val realContext = requireNotNull(context as Context) {
        "Context must be provided"
    }
    return AppLeveled.getDatastore {
        realContext.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}