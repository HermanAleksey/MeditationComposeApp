package com.example.meditationcomposeapp.data_source.data_store

import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    suspend fun writeLogin(value: String)

    fun readLogin(): Flow<String>

    suspend fun writePassword(value: String)

    fun readPassword(): Flow<String>

    suspend fun writeLastUpdateVersion(value: String)

    fun readLastUpdateVersion(): Flow<String>
}