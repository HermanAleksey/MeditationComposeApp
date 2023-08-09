package com.example.feature_toggle.internal.model.use_case

import com.justparokq.core.common.feature_toggle.FeatureToggle

//have to be implemented in the main module, since only main module knows about all FT's
interface GetAllFeatureTogglesUseCase {

    operator fun invoke(): List<FeatureToggle>
}