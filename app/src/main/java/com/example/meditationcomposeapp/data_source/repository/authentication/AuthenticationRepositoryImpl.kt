package com.example.meditationcomposeapp.data_source.repository.authentication

import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.mappers.profile.ProfileMapper
import com.example.meditationcomposeapp.data_source.network.AuthenticationApi
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.Profile
import com.example.meditationcomposeapp.model.entity.SuccessInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authApi: AuthenticationApi,
    private val profileMapper: ProfileMapper,
) : AuthenticationRepository {
    override fun login(
        login: String,
        password: String
    ): Flow<NetworkResponse<Profile>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
//               val response = authApi.login(login, password)
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
                    emit(NetworkResponse.Success(data = profileMapper.mapFrom(response)))
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
//                val response = authApi.registration(
//                    RegistrationRequest(name = name, email = login, password = password)
//                )
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

    override fun requestPasswordRestoration(
        login: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
//                val response = authApi.requestPasswordRestore(login = login)
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
//                val response = authApi.setNewPassword(
//                    login = login,
//                    newPassword = newPassword
//                )
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

    override fun verifyCode(
        login: String,
        code: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                //todo FIX: mock getting data from web
//                val response = authApi.verifyCode(login = login, code = code)
                if (code == "11111") {
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