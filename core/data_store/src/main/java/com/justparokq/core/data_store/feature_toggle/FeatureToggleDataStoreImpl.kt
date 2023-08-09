package com.justparokq.core.data_store.feature_toggle

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.justparokq.core.common.feature_toggle.FeatureToggle
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val FT_DATA_STORE_NAME = "data_store.FEATURE_TOGGLE_DATA_STORE"
val Context.ftDataStore: DataStore<Preferences> by preferencesDataStore(name = FT_DATA_STORE_NAME)

class FeatureToggleDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : FeatureToggleDataStore {

    override suspend fun writeFeatureToggle(featureToggle: FeatureToggle, active: Boolean) {
        context.ftDataStore.edit { preferences ->
            val featureToggleKey = booleanPreferencesKey(featureToggle.getKey())
            preferences[featureToggleKey] = active
        }
    }

    override fun checkFeatureToggle(featureToggle: FeatureToggle): Flow<Boolean> {
        val featureToggleKey = booleanPreferencesKey(featureToggle.getKey())
        return context.ftDataStore.data.map { preferences ->
            preferences[featureToggleKey] ?: featureToggle.getDefaultValue()
        }
    }

    override suspend fun resetDefaultState() {
        context.ftDataStore.edit {
            it.clear()
        }
    }
}