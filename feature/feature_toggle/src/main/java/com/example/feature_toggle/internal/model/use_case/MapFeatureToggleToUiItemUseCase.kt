package com.example.feature_toggle.internal.model.use_case

import com.example.common.feature_toggle.FeatureToggle
import com.example.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface MapFeatureToggleToUiItemUseCase {

    suspend operator fun invoke(featureToggle: FeatureToggle): FeatureToggleUiItem
}

class MapFeatureToggleToUiItemUseCaseImpl @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
) : MapFeatureToggleToUiItemUseCase {

    override suspend fun invoke(featureToggle: FeatureToggle): FeatureToggleUiItem {
        featureToggle.getDefaultValue()
        val isChecked = featureToggleDataStore.checkFeatureToggle(featureToggle).first()
        val title = featureToggle.getName()
        val description = featureToggle.getDescription()

        return FeatureToggleUiItem(
            isChecked = isChecked,
            title = title,
            description = description
        )
    }
}