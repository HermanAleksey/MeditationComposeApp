package com.example.feature_toggle.internal.model.interactor

import com.example.common.feature_toggle.FeatureToggle
import com.example.common.mapper.BidirectionalSuspendableMapper
import com.example.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import com.example.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import javax.inject.Inject

class FeatureToggleUiItemInteractorImpl @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
    private val getAllFeatureTogglesUseCase: GetAllFeatureTogglesUseCase,
    private val featureToggleMapper: BidirectionalSuspendableMapper<FeatureToggle, FeatureToggleUiItem>,
) : FeatureToggleUiItemInteractor {

    override suspend fun getAllFeatureToggles(): List<FeatureToggleUiItem> {
        return getAllFeatureTogglesUseCase()
            .map { featureToggleMapper.mapTo(it) }
    }

    override suspend fun updateFeatureToggleState(
        featureToggle: FeatureToggleUiItem,
        isSelected: Boolean,
    ) {
        featureToggleMapper.mapFrom(featureToggle).let {
            featureToggleDataStore.writeFeatureToggle(it, isSelected)
        }
    }
}