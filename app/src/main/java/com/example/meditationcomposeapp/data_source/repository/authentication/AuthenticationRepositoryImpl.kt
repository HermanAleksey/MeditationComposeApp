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
                kotlinx.coroutines.delay(3000)
                //todo FIX: mock getting data from web
//               val response = authApi.login(login, password)
                when {
                    login != "login" || password != "pass" -> {
                        emit(NetworkResponse.Failure(error = "Invalid login or password!"))
                    }
                    else -> {
                        val response = LoginUserResponse(
                            "User Name",
                            "https://static.vecteezy.com/packs/media/components/global/search-explore-nav/img/vectors/term-bg-1-666de2d941529c25aa511dc18d727160.jpg",
                            "London",
                            listOf()
                        )
                        emit(NetworkResponse.Success(data = profileMapper.mapFrom(response)))
                    }
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

    override fun register(
        name: String,
        login: String,
        password: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                kotlinx.coroutines.delay(2000)
                //todo FIX: mock getting data from web
                when {
                    login == "login" -> {
                        emit(
                            NetworkResponse.Success(
                                SuccessInfo(
                                    false,
                                    "User with this email already exist! Try to log in"
                                )
                            )
                        )
                    }
                    else -> {
                        val response = SuccessInfo(true, null)
                        emit(NetworkResponse.Success(response))
                    }
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

    override fun requestPasswordRestoration(
        login: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                kotlinx.coroutines.delay(2000)
                //todo FIX: mock getting data from web
//                val response = authApi.requestPasswordRestore(login = login)
                when {
                    login != "login" -> {
                        emit(
                            NetworkResponse.Success(
                                SuccessInfo(
                                    false,
                                    "Account with such login doesn't exist."
                                )
                            )
                        )
                    }
                    else -> {
                        val response = SuccessInfo(true, null)
                        emit(NetworkResponse.Success(response))
                    }
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
                kotlinx.coroutines.delay(2000)

                when {
                    newPassword == "error" -> {
                        emit(
                            NetworkResponse.Success(
                                SuccessInfo(
                                    false,
                                    "Internal server error"
                                )
                            )
                        )
                    }
                    else -> {
                        val response = SuccessInfo(true, null)
                        emit(NetworkResponse.Success(response))
                    }
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

    override fun verifyCode(
        login: String,
        code: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                kotlinx.coroutines.delay(2000)

                //todo FIX: mock getting data from web
//                val response = authApi.verifyCode(login = login, code = code)
                when {
                    code == "11111" -> {
                        emit(
                            NetworkResponse.Success(
                                SuccessInfo(false, "Incorrect code.")
                            )
                        )
                    }
                    else -> {
                        val response = SuccessInfo(true, null)
                        emit(NetworkResponse.Success(response))
                    }
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