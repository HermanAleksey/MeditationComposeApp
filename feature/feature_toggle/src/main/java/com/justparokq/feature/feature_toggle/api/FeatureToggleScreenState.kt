package com.justparokq.feature.feature_toggle.api

import com.justparokq.core.common.mvi.MviState
import com.justparokq.core.common.utils.emptyString
import com.justparokq.feature.feature_toggle.internal.entity.FeatureToggleUiItem

data class FeatureToggleScreenState(
    val list: List<FeatureToggleUiItem> = emptyList(),
    val snackBarMessage: String = emptyString()
) : MviState
