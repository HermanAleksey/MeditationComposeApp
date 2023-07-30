package com.example.core.data_store.di

import com.example.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.core.data_store.feature_toggle.FeatureToggleDataStoreImpl
import com.example.core.data_store.user.UserDataStore
import com.example.core.data_store.user.UserDataStoreImpl
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
}