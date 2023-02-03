package com.example.core.updates_history

import com.example.core.updates_history.use_case.GetAppUpdatesHistoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UpdateHistoryUseCaseModule {
    //todo create use-cases for rest of functionality

    @Binds
    abstract fun provideGetAppUpdatesHistoryUseCase(
        implementation: GetAppUpdatesHistoryUseCase
    ): GetAppUpdatesHistoryUseCase

}