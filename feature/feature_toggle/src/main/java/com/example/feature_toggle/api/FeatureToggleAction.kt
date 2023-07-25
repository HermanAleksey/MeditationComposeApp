package com.example.feature_toggle.api

import com.example.common.mvi.MviAction
import com.example.feature_toggle.internal.model.FeatureToggleUiItem

interface FeatureToggleAction : MviAction {

    data class ToggleClicked(
        val featureToggle: FeatureToggleUiItem,
    ) : FeatureToggleAction

    data class ItemLongClick(
        val featureToggle: FeatureToggleUiItem,
    ) : FeatureToggleAction
}