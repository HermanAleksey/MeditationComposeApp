package com.justparokq.core.authentication_source.api

import com.justparokq.core.authentication_source.api.model.LoginRequest
import com.justparokq.core.authentication_source.api.model.LoginUserResponse
import com.justparokq.core.authentication_source.api.model.RegistrationRequest
import com.justparokq.core.authentication_source.api.model.ResetPasswordRequest
import com.justparokq.core.authentication_source.api.model.RestorePasswordRequest
import com.justparokq.core.authentication_source.api.model.VerifyCodeRequest
import com.example.network.SuccessInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationApi {

    @GET("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginUserResponse

    @POST("auth/registration")
    suspend fun registration(
        @Body registrationRequest: RegistrationRequest,
    ): SuccessInfo

    @GET("auth/restore")
    suspend fun requestPasswordRestore(
        @Body restoreRequest: RestorePasswordRequest,
    ): SuccessInfo

    @GET("auth/code")
    suspend fun verifyCode(
        @Body verifyCodeRequest: VerifyCodeRequest,
    ): SuccessInfo

    @POST("auth/reset")
    suspend fun setNewPassword(
        @Body resetPasswordRequest: ResetPasswordRequest,
    ): SuccessInfo
}