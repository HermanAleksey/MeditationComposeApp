package com.example.feature_toggle.api

import com.justparokq.core.common.feature_toggle.FeatureToggle
import com.justparokq.core.common.mapper.BidirectionalSuspendableMapper
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import com.example.feature_toggle.internal.model.interactor.FeatureToggleUiItemInteractor
import com.example.feature_toggle.internal.model.interactor.FeatureToggleUiItemInteractorImpl
import com.example.feature_toggle.internal.model.mapper.FeatureToggleMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureToggleScreenModule {

    @Binds
    abstract fun provideGetAllFeatureTogglesUseCase(
        implementation: FeatureToggleUiItemInteractorImpl,
    ): FeatureToggleUiItemInteractor

    @Binds
    abstract fun provideMapFeatureToggleToUiItemUseCase(
        implementation: FeatureToggleMapper,
    ): BidirectionalSuspendableMapper<FeatureToggle, FeatureToggleUiItem>
}