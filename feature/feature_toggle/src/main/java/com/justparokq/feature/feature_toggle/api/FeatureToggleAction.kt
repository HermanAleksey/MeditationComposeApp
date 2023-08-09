package com.justparokq.feature.feature_toggle.api

import com.justparokq.core.common.mvi.MviAction
import com.justparokq.feature.feature_toggle.internal.entity.FeatureToggleUiItem

interface FeatureToggleAction : MviAction {

    data class ToggleClicked(
        val featureToggle: FeatureToggleUiItem,
        val isSelected: Boolean,
    ) : FeatureToggleAction

    data class ItemLongClick(
        val featureToggle: FeatureToggleUiItem,
    ) : FeatureToggleAction
}