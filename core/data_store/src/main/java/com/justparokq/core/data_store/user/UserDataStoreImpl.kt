package com.justparokq.core.data_store.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val USER_DATA_STORE = "data_store.USER_DATA_STORE"
val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATA_STORE)

enum class UserDataStoreField {
    LOGIN, PASSWORD, LAST_UPDATE_VERSION
}

class UserDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : UserDataStore {
    override suspend fun writeLogin(value: String) {
        context.userDataStore.edit { preferences ->
            val loginKey = stringPreferencesKey(UserDataStoreField.LOGIN.name)
            preferences[loginKey] = value
        }
    }

    override fun readLogin(): Flow<String> {
        val loginKey = stringPreferencesKey(UserDataStoreField.LOGIN.name)
        return context.userDataStore.data.map { preferences ->
            preferences[loginKey] ?: ""
        }
    }

    override suspend fun writePassword(value: String) {
        context.userDataStore.edit { preferences ->
            val passwordKey = stringPreferencesKey(UserDataStoreField.PASSWORD.name)
            preferences[passwordKey] = value
        }
    }

    override fun readPassword(): Flow<String> {
        val passwordKey = stringPreferencesKey(UserDataStoreField.PASSWORD.name)
        return context.userDataStore.data.map { preferences ->
            preferences[passwordKey] ?: ""
        }
    }

    override suspend fun writeLastUpdateVersion(value: String) {
        context.userDataStore.edit { preferences ->
            val lastInstalledVersionKey =
                stringPreferencesKey(UserDataStoreField.LAST_UPDATE_VERSION.name)
            preferences[lastInstalledVersionKey] = value
        }
    }

    override fun readLastUpdateVersion(): Flow<String> {
        val lastInstalledVersionKey =
            stringPreferencesKey(UserDataStoreField.LAST_UPDATE_VERSION.name)
        return context.userDataStore.data.map { preferences ->
            preferences[lastInstalledVersionKey] ?: INITIAL_VERSION
        }
    }

    companion object {
        private const val INITIAL_VERSION = "0.0.0"
    }
}

