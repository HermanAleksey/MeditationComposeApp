package com.example.feature_toggle.internal.model.use_case

import com.example.common.feature_toggle.FeatureToggle
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem

interface GetFeatureToggleByUiItemUseCase {

    operator fun invoke(featureToggle: FeatureToggleUiItem): FeatureToggle
}