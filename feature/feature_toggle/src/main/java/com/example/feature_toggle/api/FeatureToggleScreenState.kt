package com.example.feature_toggle.api

import com.example.common.mvi.MviState
import com.example.feature_toggle.internal.model.FeatureToggleUiItem

data class FeatureToggleScreenState(
    val list: List<FeatureToggleUiItem> = emptyList(),
) : MviState
