package com.example.meditationcomposeapp.data_source.network

import com.example.meditationcomposeapp.model.network.RegistrationRequest
import com.example.meditationcomposeapp.model.network.UpdatePasswordRequest
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationApi {

    @GET("user/login")
    fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): Unit

    @POST("user/registration")
    fun registration(
        @Body registrationRequest: RegistrationRequest
    ): Unit

    @GET("user/request_password_restore")
    fun requestPasswordRestore(
        @Field("login") login: String
    )

    @GET("user/verify_code")
    fun verifyCode(
        @Field("first_name") login: String, code: String
    ): Unit

    @POST("user/reset_password")
    fun setNewPassword(
        @Field("token") token: String,
        @Body updatePasswordRequest: UpdatePasswordRequest
    )
}