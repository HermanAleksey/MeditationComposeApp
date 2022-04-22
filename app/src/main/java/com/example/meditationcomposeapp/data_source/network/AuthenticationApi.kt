package com.example.meditationcomposeapp.data_source.network

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.entity.RegistrationRequest
import com.example.meditationcomposeapp.model.entity.SuccessInfo
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationApi {

    @GET("user/login")
    fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): LoginUserResponse

    @POST("user/registration")
    fun registration(
        @Body registrationRequest: RegistrationRequest
    ): SuccessInfo

    @GET("user/request_password_restore")
    fun requestPasswordRestore(
        @Field("login") login: String
    ): SuccessInfo

    @GET("user/verify_code")
    fun verifyCode(
        @Field("first_name") login: String,
        @Field("code") code: String
    ): SuccessInfo

    @POST("user/reset_password")
    fun setNewPassword(
        @Field("login") login: String,
        @Body newPassword: String
    ): SuccessInfo
}