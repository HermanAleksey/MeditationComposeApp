package com.example.meditationcomposeapp.data_source.repository.random_data

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.data_source.network.RandomDataApi
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RandomDataRepositoryImpl @Inject constructor(
    private val randomDataApi: RandomDataApi,
    private val beerMapper: Mapper<Beer, BeerResponse>
) : RandomDataRepository{
    override fun getRandomBeer(): Flow<NetworkResponse<Beer>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                val response = randomDataApi.getRandomBeer()
                emit(NetworkResponse.Success(data = beerMapper.mapFrom(response)))
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

    override fun getBeerList(size: Int): Flow<NetworkResponse<List<Beer>>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                val response = randomDataApi.getBeerList(size).map { beerResponse ->
                    beerMapper.mapFrom(beerResponse)
                }
                emit(NetworkResponse.Success(data = response))
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