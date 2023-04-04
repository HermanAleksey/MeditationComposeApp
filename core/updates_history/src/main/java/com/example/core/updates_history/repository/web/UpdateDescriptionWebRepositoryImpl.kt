package com.example.core.updates_history.repository.web

import com.example.core.model.NetworkResponse
import com.example.core.updates_history.model.UpdateDescriptionResponse
import com.example.core.updates_history.source.UpdatesHistoryApi
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
        versionName = "0.0.1",
        updateReleaseTime = 1667034395445,
        updateTitle = "Project initialization!",
        updateDescription = "Project was created. This update created in order to show origin.",
    )
    val version0_5_0 = UpdateDescriptionResponse(
        versionName = "0.5.0",
        updateReleaseTime = 1667034395445,
        updateTitle = "A lot of Beer!",
        updateDescription = "Added new beer api with more details. Also detailed screen were added for" +
                "each beer! Just click on it. Button on items of the list were meant to navigate to" +
                "beer page on an online store or similar.",
    )
    val version0_6_0 = UpdateDescriptionResponse(
        versionName = "0.6.0",
        updateReleaseTime = 1667138968792,
        updateTitle = "Update notes!",
        updateDescription = "Now you can see what is new in the app with less effort! Also you can check updates history.",
    )
    val version0_6_1 = UpdateDescriptionResponse(
        versionName = "0.6.1",
        updateReleaseTime = 1667152000868,
        updateTitle = "Bug fix",
        updateDescription = "Bug with infinite update description pop-up fixed :)",
    )
    val version0_7_2 = UpdateDescriptionResponse(
        versionName = "0.7.2",
        updateReleaseTime = 1678638488581,
        updateTitle = "Bug fixes",
        updateDescription = "Overall bug fixes and performance improvements",
    )
    val version0_8_1 = UpdateDescriptionResponse(
        versionName = "0.8.1",
        updateReleaseTime = 1680528009127,
        updateTitle = "Design update",
        updateDescription = "App design update: new typography, some color palette changes.\n" +
                "Changed the work of \"Update History\".\n" +
                "Removed profile screen since it was unnecessary.",
    )
    val version0_8_2 = UpdateDescriptionResponse(
        versionName = "0.8.2",
        updateReleaseTime = 1680595025001,
        updateTitle = "Bug fixes",
        updateDescription = "Support of custom image selection for the shuffle puzzle on all versions of Android",
    )
    val map = listOf(
        version0_0_1,
        version0_5_0,
        version0_6_0,
        version0_6_1,
        version0_7_2,
        version0_8_1,
        version0_8_2,
    )

    val list = map.toTypedArray().copyOfRange(
        map.indexOfFirst { it.versionName == startFromVersion } + 1,
        map.size
    )

    return list.toList()
}