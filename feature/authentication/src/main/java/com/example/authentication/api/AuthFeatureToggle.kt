package com.example.authentication.api

import com.example.common.feature_toggle.FeatureToggle

sealed interface AuthFeatureToggle : FeatureToggle {
    /**
     * FT to select the data source for flow authentication. (local or web)
     * */
    object WebDataSourceFT : AuthFeatureToggle
}