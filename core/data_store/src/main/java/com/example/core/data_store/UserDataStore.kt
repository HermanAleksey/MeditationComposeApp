package com.example.core.data_store

import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    //todo It's better to store token
    suspend fun writeLogin(value: String)

    fun readLogin(): Flow<String>

    suspend fun writePassword(value: String)

    fun readPassword(): Flow<String>

    suspend fun writeLastUpdateVersion(value: String)

    fun readLastUpdateVersion(): Flow<String>
}