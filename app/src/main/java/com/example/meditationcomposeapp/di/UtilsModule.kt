package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.model.utils.FieldValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {

    @Binds
    abstract fun provideValidator(
        implementation: FieldValidator
    ): FieldValidator
}