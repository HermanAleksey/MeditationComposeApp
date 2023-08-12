package com.justparokq.core.data_store.feature_toggle

import com.justparokq.core.common.feature_toggle.FeatureToggle
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface IsFeatureToggleActiveUseCase {

    suspend operator fun invoke(ft: FeatureToggle): Boolean
}

class IsFeatureToggleActiveUseCaseImpl @Inject constructor(
    private val ftDataStore: FeatureToggleDataStore,
) : IsFeatureToggleActiveUseCase {

    override suspend fun invoke(ft: FeatureToggle): Boolean {
        return ftDataStore.checkFeatureToggle(ft).first()
    }
}