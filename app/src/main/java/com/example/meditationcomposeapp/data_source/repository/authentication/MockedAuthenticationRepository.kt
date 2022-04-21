package com.example.meditationcomposeapp.data_source.repository.authentication

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.entity.RegistrationRequest
import com.example.meditationcomposeapp.data_source.entity.SuccessInfo
import com.example.meditationcomposeapp.data_source.entity.UpdatePasswordRequest
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MockedAuthenticationRepository @Inject constructor() : AuthenticationRepository {
    override fun login(
        login: String,
        password: String
    ): Flow<NetworkResponse<LoginUserResponse>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
                if (login != "login" && password != "password") {
                    emit(NetworkResponse.Failure(error = "Incorrect login or password"))
                    return@flow
                } else {
                    val response = LoginUserResponse(
                        "User Name",
                        "https://static.vecteezy.com/packs/media/components/global/search-explore-nav/img/vectors/term-bg-1-666de2d941529c25aa511dc18d727160.jpg",
                        "London",
                        listOf()
                    )
                    emit(NetworkResponse.Success(data = response))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun registrate(
        name: String,
        login: String,
        password: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
                val response = SuccessInfo(true, null)
                emit(NetworkResponse.Success(response))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun requestPasswordRestoration(login: String): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
                if (login != "login") {
                    emit(NetworkResponse.Failure(error = "ERROR"))
                } else {
                    val response = SuccessInfo(true, null)
                    emit(NetworkResponse.Success(response))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun setNewPassword(
        login: String,
        newPassword: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
                val response = SuccessInfo(true, null)
                emit(NetworkResponse.Success(response))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun verifyCode(login: String, code: String): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
                if (code == "11111"){
                    emit(NetworkResponse.Failure(error = "Wrong code"))
                } else {
                    val response = SuccessInfo(true, null)
                    emit(NetworkResponse.Success(response))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = "Couldn't load data"))
            }
            emit(NetworkResponse.Loading(false))
        }
    }
}