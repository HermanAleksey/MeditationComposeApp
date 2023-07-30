package com.example.feature_toggle.internal.model.interactor

import com.example.feature_toggle.internal.entity.FeatureToggleUiItem

interface FeatureToggleUiItemInteractor {

    suspend fun getAllFeatureToggles(): List<FeatureToggleUiItem>

    suspend fun updateFeatureToggleState(featureToggle: FeatureToggleUiItem, isSelected: Boolean)
}