package com.example.feature_toggle.internal.model.mapper

import com.justparokq.core.common.feature_toggle.FeatureToggle
import com.justparokq.core.common.mapper.BidirectionalSuspendableMapper
import com.justparokq.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import com.example.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FeatureToggleMapper @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
    private val getAllFeatureTogglesUseCase: GetAllFeatureTogglesUseCase,
) : BidirectionalSuspendableMapper<FeatureToggle, FeatureToggleUiItem> {

    override suspend fun mapFrom(objectFrom: FeatureToggleUiItem): FeatureToggle {
        return getAllFeatureTogglesUseCase().find { it.getName() == objectFrom.title }
            ?: throw Exception(
                "This FeatureToggleUiItem was created on base " +
                        "of not existing FeatureToggle object."
            )
    }

    override suspend fun mapTo(objectFrom: FeatureToggle): FeatureToggleUiItem {
        val isChecked = featureToggleDataStore.checkFeatureToggle(objectFrom).first()
        val title = objectFrom.getName()
        val description = objectFrom.getDescription()

        return FeatureToggleUiItem(
            isChecked = isChecked,
            title = title,
            description = description
        )
    }
}