package com.example.meditationcomposeapp.data_source.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.meditationcomposeapp.data_source.data_store.DataStoreName.USER_DATA_STORE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATA_STORE)

object DataStoreName {
    const val USER_DATA_STORE = "data_store.USER_DATA_STORE"
}

enum class UserDataStoreField {
    LOGIN, PASSWORD, LAST_UPDATE_VERSION
}

class UserDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun writeLogin(value: String) {
        context.userDataStore.edit { preferences ->
            val loginKey = stringPreferencesKey(UserDataStoreField.LOGIN.name)
            preferences[loginKey] = value
        }
    }

    fun readLogin(): Flow<String> {
        val loginKey = stringPreferencesKey(UserDataStoreField.LOGIN.name)
        return context.userDataStore.data.map { preferences ->
            preferences[loginKey] ?: ""
        }
    }

    suspend fun writePassword(value: String) {
        context.userDataStore.edit { preferences ->
            val passwordKey = stringPreferencesKey(UserDataStoreField.PASSWORD.name)
            preferences[passwordKey] = value
        }
    }

    fun readPassword(): Flow<String> {
        val passwordKey = stringPreferencesKey(UserDataStoreField.PASSWORD.name)
        return context.userDataStore.data.map { preferences ->
            preferences[passwordKey] ?: ""
        }
    }

    suspend fun writeLastUpdateVersion(value: String) {
        context.userDataStore.edit { preferences ->
            val lastInstalledVersionKey = stringPreferencesKey(UserDataStoreField.LAST_UPDATE_VERSION.name)
            preferences[lastInstalledVersionKey] = value
        }
    }

    fun readLastUpdateVersion(): Flow<String> {
        val lastInstalledVersionKey = stringPreferencesKey(UserDataStoreField.LAST_UPDATE_VERSION.name)
        return context.userDataStore.data.map { preferences ->
            preferences[lastInstalledVersionKey] ?: "0.0.0"
        }
    }
}

