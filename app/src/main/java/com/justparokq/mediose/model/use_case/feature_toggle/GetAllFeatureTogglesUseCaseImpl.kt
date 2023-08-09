package com.justparokq.mediose.model.use_case.feature_toggle

import com.justparokq.feature.authentication.api.AuthFeatureToggle
import com.justparokq.core.common.feature_toggle.FeatureToggle
import com.justparokq.core.data_store.feature_toggle.FeatureToggleDataStore
import com.justparokq.feature.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import javax.inject.Inject

class GetAllFeatureTogglesUseCaseImpl @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
) : GetAllFeatureTogglesUseCase {

    override operator fun invoke(): List<FeatureToggle> {
        featureToggleDataStore
        return listOf(
            AuthFeatureToggle.WebDataSourceFT,
            AuthFeatureToggle.AuthFlowValidationFT,
        )
    }
}