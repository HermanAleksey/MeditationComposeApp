package com.example.feature_toggle.internal.model.interactor

import com.example.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import com.example.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import com.example.feature_toggle.internal.model.use_case.GetFeatureToggleByUiItemUseCase
import com.example.feature_toggle.internal.model.use_case.MapFeatureToggleToUiItemUseCase
import javax.inject.Inject

class FeatureToggleUiItemInteractorImpl @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
    private val getAllFeatureTogglesUseCase: GetAllFeatureTogglesUseCase,
    private val getFeatureToggleByUiItemUseCase: GetFeatureToggleByUiItemUseCase,
    private val mapFeatureToggleToUiItemUseCase: MapFeatureToggleToUiItemUseCase,
) : FeatureToggleUiItemInteractor {

    override suspend fun getAllFeatureToggles(): List<FeatureToggleUiItem> {
        return getAllFeatureTogglesUseCase()
            .map {
                mapFeatureToggleToUiItemUseCase(it)
            }
    }

    override suspend fun updateFeatureToggleState(
        featureToggle: FeatureToggleUiItem,
        isSelected: Boolean,
    ) {
        getFeatureToggleByUiItemUseCase(featureToggle).let {
            featureToggleDataStore.writeFeatureToggle(it, isSelected)
        }
    }
}