package com.example.punk_api.api.repository

import com.example.common.mapper.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.network.NetworkResponse
import com.example.punk_api.api.model.BeerResponse
import com.example.punk_api.internal.PunkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PunkRepositoryImpl @Inject constructor(
    private val punkApi: PunkApi,
    private val beerMapper: Mapper<Beer, BeerResponse>,
) : PunkRepository {

    override suspend fun getBeerById(page: Int): Flow<NetworkResponse<Beer>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                val response = punkApi.getBeerById(page)
                emit(NetworkResponse.Success(data = beerMapper.mapFrom(response.first())))
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

    override suspend fun getRandomBeer(): Flow<NetworkResponse<Beer>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            try {
                val response = punkApi.getRandomBeer()
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

    override suspend fun getBeers(
        page: Int,
        pageSize: Int,
    ): List<Beer> = punkApi.getBeers(page, pageSize).map {
        beerMapper.mapFrom(it)
    }
}