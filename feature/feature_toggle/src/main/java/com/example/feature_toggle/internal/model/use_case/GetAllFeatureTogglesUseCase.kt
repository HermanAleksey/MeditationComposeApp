package com.example.feature_toggle.internal.model.use_case

import com.example.feature_toggle.internal.entity.FeatureToggleUiItem

//have to be implemented in the main module, since only main module knows about all FT's
interface GetAllFeatureTogglesUseCase {

    operator fun invoke(): List<FeatureToggleUiItem>
}