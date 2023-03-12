package com.example.core.updates_history.repository.web

import com.example.core.updates_history.source.UpdatesHistoryApi
import com.example.core.updates_history.model.UpdateDescriptionResponse
import com.example.core.model.NetworkResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateDescriptionWebRepositoryImpl @Inject constructor(
    private val updatesHistoryApi: UpdatesHistoryApi,
) : UpdateDescriptionWebRepository {

    override fun getAppUpdates(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionResponse>>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                delay(100)

                //mock since don't have backend for now
                if (true) {
                    val response = getVersionDescriptions(startFromVersion)
                    emit(NetworkResponse.Success(response))
                } else {
                    val response = updatesHistoryApi.getAppUpdatesHistory(startFromVersion)
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
    val version0_6_2 = UpdateDescriptionResponse(
        id = 5,
        versionName = "0.6.2",
        updateReleaseTime = 1678638488581,
        updateTitle = "Bug fixes",
        updateDescription = "Overall bug fixes and performance improvements",
    )
    val map = mapOf(
        "0.0.1" to version0_0_1,
        "0.5.0" to version0_5_0,
        "0.6.0" to version0_6_0,
        "0.6.1" to version0_6_1,
        "0.6.2" to version0_6_2,
    )

    val list = map.values.toTypedArray().copyOfRange(
        map.keys.indexOf(startFromVersion) + 1,
        map.size
    )

    return list.toList()
}