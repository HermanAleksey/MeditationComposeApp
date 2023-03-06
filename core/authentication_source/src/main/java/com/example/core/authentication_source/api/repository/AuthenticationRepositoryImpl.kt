package com.example.core.authentication_source.api.repository

import com.example.core.authentication_api.BuildConfig
import com.example.core.authentication_source.api.AuthenticationApi
import com.example.core.authentication_source.api.mapper.ProfileMapper
import com.example.core.authentication_source.api.model.LoginUserResponse
import com.example.core.authentication_source.api.model.RegistrationRequest
import com.example.core.model.NetworkResponse
import com.example.core.model.authentication.Profile
import com.example.network.SuccessInfo
import kotlinx.coroutines.delay
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
        password: String,
    ): Flow<NetworkResponse<Profile>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                delay(2000)

                if (!BuildConfig.ENABLE_VALIDATION) {
                    val response = LoginUserResponse(
                        "User Name",
                        "https://static.vecteezy.com/packs/media/components/global/search-explore-nav/img/vectors/term-bg-1-666de2d941529c25aa511dc18d727160.jpg",
                        "London",
                        "listOf()"
                    )
                    emit(NetworkResponse.Success(data = profileMapper.mapFrom(response)))
                } else {
                    val response = authApi.login(login, password)
                    emit(NetworkResponse.Success(data = profileMapper.mapFrom(response)))
                }

            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun register(
        name: String,
        login: String,
        password: String,
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                delay(1000)
                if (!BuildConfig.ENABLE_VALIDATION) {
                    emit(
                        NetworkResponse.Success(
                            SuccessInfo(
                                true,
                                null
                            )
                        )
                    )
                } else {
                    val webResponse = authApi.registration(
                        RegistrationRequest(
                            name = name, email = login, password = password
                        )
                    )

                    val response = NetworkResponse.Success(
                        webResponse
                    )

                    emit(response)
                }

            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun requestPasswordRestoration(
        login: String,
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                delay(1000)

                if (!BuildConfig.ENABLE_VALIDATION) {
                    val response = SuccessInfo(true, null)
                    emit(NetworkResponse.Success(response))
                } else {
                    val response = authApi.requestPasswordRestore(login = login)
                    emit(NetworkResponse.Success(response))
                }

            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun setNewPassword(
        login: String,
        newPassword: String,
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                delay(1000)

                if (!BuildConfig.ENABLE_VALIDATION) {
                    val response = SuccessInfo(true, null)
                    emit(NetworkResponse.Success(response))
                } else {
                    val response = authApi.setNewPassword(login = login, newPassword = newPassword)
                    emit(NetworkResponse.Success(response))
                }

            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            }
            emit(NetworkResponse.Loading(false))
        }
    }

    override fun verifyCode(
        login: String,
        code: String,
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                delay(1000)

                if (!BuildConfig.ENABLE_VALIDATION) {
                    val response = SuccessInfo(true, null)
                    emit(NetworkResponse.Success(response))
                } else {
                    val response = authApi.verifyCode(login = login, code = code)
                    emit(NetworkResponse.Success(response))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(NetworkResponse.Failure(error = e.message))
            }
            emit(NetworkResponse.Loading(false))
        }
    }
}
