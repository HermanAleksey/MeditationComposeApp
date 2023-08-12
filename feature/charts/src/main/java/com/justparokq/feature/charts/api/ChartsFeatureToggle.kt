package com.justparokq.feature.charts.api

import com.justparokq.core.common.feature_toggle.FeatureToggle

interface ChartsFeatureToggle : FeatureToggle {

    object WebDataSourceFT : ChartsFeatureToggle {
        override fun getDefaultValue(): Boolean = false

        override fun getName(): String {
            return "WEB_LINE_CHART_DATA_SOURCE"
        }

        override fun getDescription(): String {
            return "When FT is turned on - use WebSocket connection data for LineChart" +
                    " demo in ChartDemoScreen. Otherwise use locally generated data"
        }
    }
}