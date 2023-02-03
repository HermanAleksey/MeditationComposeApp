package com.example.core.data_store

import com.example.core.data_store.use_case.ClearAuthDataUseCase
import com.example.core.data_store.use_case.ClearAuthDataUseCaseImpl
import dagger.Binds
import dagger.Module


@Module
abstract class DataStoreUseCaseModule {

    @Binds
    abstract fun provideClearAuthDataUseCase(
        implementation: ClearAuthDataUseCaseImpl
    ): ClearAuthDataUseCase

}