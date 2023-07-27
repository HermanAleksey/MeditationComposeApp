package com.example.feature_toggle.api

import com.example.feature_toggle.internal.model.interactor.FeatureToggleUiItemInteractor
import com.example.feature_toggle.internal.model.interactor.FeatureToggleUiItemInteractorImpl
import com.example.feature_toggle.internal.model.use_case.MapFeatureToggleToUiItemUseCase
import com.example.feature_toggle.internal.model.use_case.MapFeatureToggleToUiItemUseCaseImpl
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
        implementation: MapFeatureToggleToUiItemUseCaseImpl,
    ): MapFeatureToggleToUiItemUseCase
}