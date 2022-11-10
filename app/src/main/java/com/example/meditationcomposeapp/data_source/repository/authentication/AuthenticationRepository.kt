package com.example.meditationcomposeapp.data_source.repository.authentication

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.entity.UpdateDescriptionResponse
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.Profile
import com.example.meditationcomposeapp.model.entity.login_flow.SuccessInfo
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

    /**
     * Returns list of update descriptions since [startFromVersion] version excluded
     * */
    fun getAppUpdates(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionResponse>>>
}