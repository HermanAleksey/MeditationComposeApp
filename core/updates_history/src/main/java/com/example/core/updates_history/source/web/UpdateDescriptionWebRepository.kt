package com.example.core.updates_history.source.web

import com.example.core.updates_history.model.UpdateDescriptionResponse
import com.example.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface UpdateDescriptionWebRepository {

    /**
     * Returns list of update descriptions since [startFromVersion] version excluded
     * */
    fun getAppUpdates(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionResponse>>>
}