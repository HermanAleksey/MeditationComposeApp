package com.example.meditationcomposeapp.data_source.network

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.entity.RegistrationRequest
import com.example.meditationcomposeapp.model.entity.login_flow.SuccessInfo
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationApi {

    @GET("user/login")
    suspend fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): LoginUserResponse

    @POST("user/registration")
    suspend fun registration(
        @Body registrationRequest: RegistrationRequest
    ): SuccessInfo

    @GET("user/request_password_restore")
    suspend fun requestPasswordRestore(
        @Field("login") login: String
    ): SuccessInfo

    @GET("user/verify_code")
    suspend fun verifyCode(
        @Field("first_name") login: String,
        @Field("code") code: String
    ): SuccessInfo

    @POST("user/reset_password")
    suspend fun setNewPassword(
        @Field("login") login: String,
        @Body newPassword: String
    ): SuccessInfo
}