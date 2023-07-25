package com.example.feature_toggle.api

import com.example.common.mvi.MviAction
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem

interface FeatureToggleAction : MviAction {

    data class ToggleClicked(
        val featureToggle: FeatureToggleUiItem,
        val isSelected: Boolean,
    ) : FeatureToggleAction

    data class ItemLongClick(
        val featureToggle: FeatureToggleUiItem,
    ) : FeatureToggleAction
}