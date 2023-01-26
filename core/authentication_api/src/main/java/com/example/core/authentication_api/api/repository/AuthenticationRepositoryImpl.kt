package com.example.core.authentication_api.api.repository

import com.example.core.authentication_api.api.AuthenticationApi
import com.example.core.authentication_api.api.mapper.ProfileMapper
import com.example.core.authentication_api.api.model.LoginUserResponse
import com.example.core.authentication_api.api.model.RegistrationRequest
import com.example.core.authentication_api.api.model.UpdateDescriptionResponse
import com.example.core.model.authentication.Profile
import com.example.network.NetworkResponse
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

    override fun getAppUpdates(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionResponse>>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                delay(100)

                if (!BuildConfig.ENABLE_VALIDATION) {
                    val response = getVersionDescriptions(startFromVersion)
                    emit(NetworkResponse.Success(response))
                } else {
                    val response = authApi.getAppUpdatesHistory(startFromVersion)
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

//todo move updates logic into separate module
//improvised response from back-end
fun getVersionDescriptions(startFromVersion: String): List<UpdateDescriptionResponse> {
    val version0_0_1 = UpdateDescriptionResponse(
        id = 1,
        versionName = "0.0.1",
        updateReleaseTime = 1667034395445,
        updateTitle = "Project initialization!",
        updateDescription = "Project was created. This update created in order to show origin.",
    )
    val version0_5_0 = UpdateDescriptionResponse(
        id = 2,
        versionName = "0.5.0",
        updateReleaseTime = 1667034395445,
        updateTitle = "A lot of Beer!",
        updateDescription = "Added new beer api with more details. Also detailed screen were added for" +
                "each beer! Just click on it. Button on items of the list were meant to navigate to" +
                "beer page on an online store or similar.",
    )
    val version0_6_0 = UpdateDescriptionResponse(
        id = 3,
        versionName = "0.6.0",
        updateReleaseTime = 1667138968792,
        updateTitle = "Update notes!",
        updateDescription = "Now you can see what is new in the app with less effort! Also you can check updates history.",
    )
    val version0_6_1 = UpdateDescriptionResponse(
        id = 4,
        versionName = "0.6.1",
        updateReleaseTime = 1667152000868,
        updateTitle = "Bug fix",
        updateDescription = "Bug with infinite update description pop-up fixed :)",
    )
    val map = mapOf(
        "0.0.1" to version0_0_1,
        "0.5.0" to version0_5_0,
        "0.6.0" to version0_6_0,
        "0.6.1" to version0_6_1,
    )

    val list = map.values.toTypedArray().copyOfRange(
        map.keys.indexOf(startFromVersion) + 1,
        map.size
    )

    return list.toList()
}