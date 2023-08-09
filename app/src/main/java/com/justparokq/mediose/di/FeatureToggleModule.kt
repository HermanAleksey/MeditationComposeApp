package com.justparokq.mediose.di

import com.justparokq.feature.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import com.justparokq.mediose.model.use_case.feature_toggle.GetAllFeatureTogglesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class FeatureToggleModule {
    @Binds
    abstract fun provideGetAllFeatureTogglesUseCase(
        implementation: GetAllFeatureTogglesUseCaseImpl,
    ): GetAllFeatureTogglesUseCase
}