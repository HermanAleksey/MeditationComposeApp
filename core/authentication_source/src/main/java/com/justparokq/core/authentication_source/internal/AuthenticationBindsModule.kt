package com.justparokq.core.authentication_source.internal

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.authentication_source.api.mapper.ProfileMapper
import com.justparokq.core.authentication_source.api.model.LoginUserResponse
import com.justparokq.core.authentication_source.api.repository.AuthenticationRepository
import com.justparokq.core.authentication_source.api.repository.AuthenticationRepositoryImpl
import com.justparokq.core.authentication_source.api.use_case.*
import com.justparokq.core.model.authentication.Profile
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationBindsModule {

    @Binds
    abstract fun provideProfileMapper(
        implementation: ProfileMapper,
    ): Mapper<Profile, LoginUserResponse>

    @Binds
    abstract fun provideAuthenticationRepository(
        implementation: AuthenticationRepositoryImpl,
    ): AuthenticationRepository

    @Binds
    abstract fun provideLoginUseCase(
        implementation: LoginUseCaseImpl,
    ): LoginUseCase

    @Binds
    abstract fun provideRegisterUseCase(
        implementation: RegisterUseCaseImpl,
    ): RegisterUseCase

    @Binds
    abstract fun provideRequestPasswordRestorationUseCase(
        implementation: RequestPasswordRestorationUseCaseImpl,
    ): RequestPasswordRestorationUseCase

    @Binds
    abstract fun provideSetNewPasswordUseCase(
        implementation: SetNewPasswordUseCaseImpl,
    ): SetNewPasswordUseCase

    @Binds
    abstract fun provideVerifyCodeUseCase(
        implementation: VerifyCodeUseCaseImpl,
    ): VerifyCodeUseCase
}