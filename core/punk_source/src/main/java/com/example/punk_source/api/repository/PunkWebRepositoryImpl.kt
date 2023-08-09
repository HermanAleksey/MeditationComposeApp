package com.example.punk_source.api.repository

import com.justparokq.core.common.mapper.Mapper
import com.example.core.model.NetworkResponse
import com.example.core.model.beer_sorts.Beer
import com.example.punk_source.api.model.web.BeerResponse
import com.example.punk_source.internal.PunkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PunkWebRepositoryImpl @Inject constructor(
    private val punkApi: PunkApi,
    private val beerMapper: Mapper<Beer, BeerResponse>,
) : PunkWebRepository {

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
                val response = punkApi.getRandomBeer().first()
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