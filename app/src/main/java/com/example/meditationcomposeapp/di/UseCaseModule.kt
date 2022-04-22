package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.model.usecase.authentication.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideLoginUseCase(
        implementation: LoginUseCaseImpl
    ): LoginUseCase

    @Binds
    abstract fun provideRegisterUseCase(
        implementation: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    abstract fun provideVerifyCodeUseCase(
        implementation: VerifyCodeUseCaseImpl
    ): VerifyCodeUseCase

    @Binds
    abstract fun provideRequestPasswordRestorationUseCase(
        implementation: RequestPasswordRestorationUseCaseImpl
    ): RequestPasswordRestorationUseCase

    @Binds
    abstract fun provideSetNewPasswordUseCase(
        implementation: SetNewPasswordUseCaseImpl
    ): SetNewPasswordUseCase

}