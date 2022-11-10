package com.example.meditationcomposeapp.model.usecase.authentication

import com.example.meditationcomposeapp.data_source.entity.UpdateDescriptionResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetAppUpdatesHistoryUseCase {

    operator fun invoke(startFromVersion: String): Flow<NetworkResponse<List<UpdateDescriptionModel>>>
}

class GetAppUpdatesHistoryUseCaseImpl @Inject constructor(
    private val repository: AuthenticationRepository,
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