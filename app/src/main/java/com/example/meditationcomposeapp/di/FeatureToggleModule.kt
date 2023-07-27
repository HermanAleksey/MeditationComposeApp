package com.example.meditationcomposeapp.di

import com.example.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import com.example.meditationcomposeapp.model.use_case.feature_toggle.GetAllFeatureTogglesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureToggleModule {
    @Binds
    abstract fun provideGetAllFeatureTogglesUseCase(
        implementation: GetAllFeatureTogglesUseCaseImpl,
    ): GetAllFeatureTogglesUseCase
}