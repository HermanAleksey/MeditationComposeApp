package com.example.core.authentication_source.api.repository

import com.example.core.model.authentication.Profile
import com.example.core.model.NetworkResponse
import com.example.network.SuccessInfo
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    /**
     * @param [login] the login of account
     * @param [password] the password of account
     * @return [LoginUserResponse] of logged user if success
     * @return error message if signing in was failed
     * */
    fun login(login: String, password: String): Flow<NetworkResponse<Profile>>

    /**
     * @param [name] the name of user
     * @param [login] the login of account
     * @param [password] the password of account
     * @return returns the [SuccessInfo] value of success or failure
     * */
    fun register(name: String, login: String, password: String): Flow<NetworkResponse<SuccessInfo>>

    /**
     * @param [login] the value of the account whose password you want to recover
     * @return returns the [SuccessInfo] value of success or failure
     * */
    fun requestPasswordRestoration(login: String): Flow<NetworkResponse<SuccessInfo>>

    /**
     * @param [login] the value of the account whose password you want to update
     * @param [newPassword] new password that has to be settled
     * @return returns the [SuccessInfo] value of success or failure
     * */
    fun setNewPassword(login: String, newPassword: String): Flow<NetworkResponse<SuccessInfo>>

    /**
     * @param [login] the value of the account that received code on mail
     * @param [code] entered by user code
     * @return returns the [SuccessInfo] value of success or failure
     * */
    fun verifyCode(login: String, code: String): Flow<NetworkResponse<SuccessInfo>>
}