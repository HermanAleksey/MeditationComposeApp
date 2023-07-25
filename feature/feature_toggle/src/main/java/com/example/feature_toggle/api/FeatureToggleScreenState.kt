package com.example.feature_toggle.api

import com.example.common.mvi.MviState
import com.example.common.utils.emptyString
import com.example.feature_toggle.internal.model.FeatureToggleUiItem

data class FeatureToggleScreenState(
    val list: List<FeatureToggleUiItem> = emptyList(),
    val snackBarMessage: String = emptyString()
) : MviState
