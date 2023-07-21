package com.example.common.feature_toggle

interface FeatureToggleProvider {

    fun isActive(featureToggle: FeatureToggle): Boolean
}