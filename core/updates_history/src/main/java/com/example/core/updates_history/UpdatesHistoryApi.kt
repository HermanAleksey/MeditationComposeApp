package com.example.core.updates_history

import com.example.core.updates_history.model.UpdateDescriptionResponse
import retrofit2.http.Field
import retrofit2.http.GET

interface UpdatesHistoryApi {

    @GET("updates")
    suspend fun getAppUpdatesHistory(
        @Field("start") startFromVersion: String,
    ): List<UpdateDescriptionResponse>
}