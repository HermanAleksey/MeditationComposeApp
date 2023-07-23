package com.example.feature_toggle.api

import com.example.common.feature_toggle.FeatureToggle
import com.example.common.mvi.MviAction

interface FeatureToggleAction : MviAction {

    data class ToggleClicked(
        val featureToggle: FeatureToggle
    ): FeatureToggleAction
}