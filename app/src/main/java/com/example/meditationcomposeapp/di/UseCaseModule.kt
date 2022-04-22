package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.model.usecase.authentication.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Provides
    abstract fun provideLoginUseCase(
        implementation: LoginUseCaseImpl
    ): LoginUseCase

    @Provides
    abstract fun provideRegisterUseCase(
        implementation: RegisterUseCaseImpl
    ): RegisterUseCase

    @Provides
    abstract fun provideVerifyCodeUseCase(
        implementation: VerifyCodeUseCaseImpl
    ): VerifyCodeUseCase

    @Provides
    abstract fun provideRequestPasswordRestorationUseCase(
        implementation: RequestPasswordRestorationUseCaseImpl
    ): RequestPasswordRestorationUseCase

    @Provides
    abstract fun provideSetNewPasswordUseCase(
        implementation: SetNewPasswordUseCaseImpl
    ): SetNewPasswordUseCase

}