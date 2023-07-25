package com.example.meditationcomposeapp.model.use_case.feature_toggle

import com.example.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import com.example.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import javax.inject.Inject

class GetAllFeatureTogglesUseCaseImpl @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
) : GetAllFeatureTogglesUseCase {

    override operator fun invoke(): List<FeatureToggleUiItem> {
        featureToggleDataStore
        return listOf(
            FeatureToggleUiItem(
                isChecked = true,
                title = "Web auth flow",
                description = "When turned on - use web data source for auth flow"
            ),
            FeatureToggleUiItem(
                isChecked = true,
                title = "Test1",
                description = "Desc1"
            ),
            FeatureToggleUiItem(
                isChecked = false,
                title = "test2",
                description = "Desc2"
            ),
        )
    }
}