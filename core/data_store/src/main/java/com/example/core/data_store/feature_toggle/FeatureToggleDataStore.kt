package com.example.core.data_store.feature_toggle

import com.example.common.feature_toggle.FeatureToggle
import kotlinx.coroutines.flow.Flow

interface FeatureToggleDataStore {

    suspend fun writeFeatureToggle(featureToggle: FeatureToggle, active: Boolean)

    fun checkFeatureToggle(featureToggle: FeatureToggle): Flow<Boolean>

    suspend fun resetDefaultState()
}
