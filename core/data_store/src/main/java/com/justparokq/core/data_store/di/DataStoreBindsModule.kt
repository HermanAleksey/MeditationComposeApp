package com.justparokq.core.data_store.di

import com.justparokq.core.data_store.feature_toggle.FeatureToggleDataStore
import com.justparokq.core.data_store.feature_toggle.FeatureToggleDataStoreImpl
import com.justparokq.core.data_store.feature_toggle.IsFeatureToggleActiveUseCase
import com.justparokq.core.data_store.feature_toggle.IsFeatureToggleActiveUseCaseImpl
import com.justparokq.core.data_store.user.UserDataStore
import com.justparokq.core.data_store.user.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreBindsModule {

    @Binds
    abstract fun provideUserDataStore(
        implementation: UserDataStoreImpl,
    ): UserDataStore

    @Binds
    abstract fun provideFeatureToggleDataStore(
        implementation: FeatureToggleDataStoreImpl,
    ): FeatureToggleDataStore

    @Binds
    abstract fun provideIsFeatureToggleActiveUseCase(
        implementation: IsFeatureToggleActiveUseCaseImpl,
    ): IsFeatureToggleActiveUseCase
}