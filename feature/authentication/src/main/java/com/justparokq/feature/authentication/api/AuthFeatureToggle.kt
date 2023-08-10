package com.justparokq.feature.authentication.api

import com.justparokq.core.common.feature_toggle.FeatureToggle

interface AuthFeatureToggle : FeatureToggle {

    object WebDataSourceFT : AuthFeatureToggle {
        override fun getDefaultValue(): Boolean = false

        override fun getName(): String {
            return "AUTH_FLOW_WEB_DATA_SOURCE"
        }

        override fun getDescription(): String {
            return "FT to select the data source for flow authentication. " +
                    "(mocked data when turned off and web when turned on)"
        }
    }

    object AuthFlowValidationFT : AuthFeatureToggle {
        override fun getDefaultValue(): Boolean = false

        override fun getName(): String {
            return "AUTH_FLOW_VALIDATION"
        }

        override fun getDescription(): String {
            return "When turned off - does not validate any inputted in TextFields value on AuthFlow"
        }
    }
}