package com.example.authentication.api

import com.example.common.feature_toggle.FeatureToggle

interface AuthFeatureToggle : FeatureToggle {

    object WebDataSourceFT : AuthFeatureToggle {
        override fun getDefaultValue(): Boolean = false

        override fun getDescription(): String {
            return "FT to select the data source for flow authentication. " +
                    "(mocked data when turned off and web when turned on)"
        }
    }
}