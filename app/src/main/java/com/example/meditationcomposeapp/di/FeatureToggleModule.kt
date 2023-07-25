package com.example.meditationcomposeapp.di

import com.example.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import com.example.feature_toggle.internal.model.use_case.UpdateFeatureToggleStateUseCase
import com.example.meditationcomposeapp.model.use_case.feature_toggle.GetAllFeatureTogglesUseCaseImpl
import com.example.meditationcomposeapp.model.use_case.feature_toggle.UpdateFeatureToggleStateUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface FeatureToggleModule {

    @Binds
    fun provideGetAllFeatureTogglesUseCase(
        impl: GetAllFeatureTogglesUseCaseImpl,
    ): GetAllFeatureTogglesUseCase

    @Binds
    fun provideUpdateFeatureToggleStateUseCase(
        impl: UpdateFeatureToggleStateUseCaseImpl,
    ): UpdateFeatureToggleStateUseCase
}