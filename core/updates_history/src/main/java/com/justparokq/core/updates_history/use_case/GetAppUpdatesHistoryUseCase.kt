package com.justparokq.core.updates_history.use_case

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.NetworkResponse
import com.justparokq.core.model.updates.UpdateDescriptionModel
import com.justparokq.core.updates_history.model.UpdateDescriptionResponse
import com.justparokq.core.updates_history.repository.web.UpdateDescriptionWebRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetAppUpdatesHistoryUseCase {

    operator fun invoke(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionModel>>>
}

class GetAppUpdatesHistoryUseCaseImpl @Inject constructor(
    private val repository: UpdateDescriptionWebRepository,
    private val mapper: Mapper<UpdateDescriptionModel, UpdateDescriptionResponse>
) : GetAppUpdatesHistoryUseCase {
    override fun invoke(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionModel>>> {
        return repository.getAppUpdates(startFromVersion).map {
            when (it) {
                is NetworkResponse.Loading -> {
                    NetworkResponse.Loading(it.isLoading)
                }
                is NetworkResponse.Success -> {
                    NetworkResponse.Success(
                        data = it.data?.map { response ->
                            mapper.mapFrom(response)
                        } ?: listOf()
                    )
                }
                is NetworkResponse.Failure -> {
                    NetworkResponse.Failure(
                        error = it.error
                    )
                }
            }
        }
    }

}