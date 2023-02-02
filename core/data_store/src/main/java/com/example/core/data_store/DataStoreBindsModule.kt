package com.example.core.data_store

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreBindsModule {

    @Binds
    abstract fun provideDataStore(
        implementation: UserDataStoreImpl,
    ): UserDataStore

}