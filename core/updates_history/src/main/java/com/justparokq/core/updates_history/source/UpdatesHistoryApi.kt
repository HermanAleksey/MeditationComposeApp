package com.justparokq.core.updates_history.source

import com.justparokq.core.updates_history.model.UpdateDescriptionResponse
import retrofit2.http.Field
import retrofit2.http.GET

interface UpdatesHistoryApi {

    @GET("updates")
    suspend fun getAppUpdatesHistory(
        @Field("start") startFromVersion: String,
    ): List<UpdateDescriptionResponse>
}