package com.example.meditationcomposeapp.model.use_case.feature_toggle

import com.example.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import com.example.feature_toggle.internal.model.use_case.UpdateFeatureToggleStateUseCase
import javax.inject.Inject

class UpdateFeatureToggleStateUseCaseImpl @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
) : UpdateFeatureToggleStateUseCase {

    override operator fun invoke(featureToggle: FeatureToggleUiItem, isSelected: Boolean) {
        featureToggleDataStore
    }
}