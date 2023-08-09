package com.justparokq.core.updates_history.repository.web

import com.justparokq.core.updates_history.model.UpdateDescriptionResponse
import com.justparokq.core.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface UpdateDescriptionWebRepository {

    /**
     * Returns list of update descriptions since [startFromVersion] version excluded
     * */
    fun getAppUpdates(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionResponse>>>
}