package com.example.meditationcomposeapp.presentation

import com.example.authentication.api.AuthFeatureToggle
import com.example.common.feature_toggle.FeatureToggle
import com.example.common.feature_toggle.FeatureToggleProvider
import javax.inject.Inject

class FeatureToggleProviderImpl @Inject constructor() : FeatureToggleProvider {

    override fun isActive(featureToggle: FeatureToggle): Boolean {
        return when (featureToggle) {
            is AuthFeatureToggle -> checkAuthFeatureToggles(featureToggle)
            else -> throw Throwable(
                "Requested feature-toggle is not implemented in FeatureToggleProvider"
            )
        }
    }

    private fun checkAuthFeatureToggles(featureToggle: AuthFeatureToggle): Boolean {
        return when (featureToggle) {
            AuthFeatureToggle.WebDataSourceFT -> true
        }
    }
}