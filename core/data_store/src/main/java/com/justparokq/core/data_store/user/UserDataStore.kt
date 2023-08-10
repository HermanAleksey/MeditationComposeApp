package com.justparokq.core.data_store.user

import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    //todo It's better to store token
    suspend fun writeLogin(value: String)

    fun readLogin(): Flow<String>

    //todo replace all work with passwords to hashCode
    suspend fun writePassword(value: String)

    fun readPassword(): Flow<String>

    suspend fun writeLastUpdateVersion(value: String)

    fun readLastUpdateVersion(): Flow<String>
}