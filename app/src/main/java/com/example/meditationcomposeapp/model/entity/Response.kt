package com.example.meditationcomposeapp.model.entity

sealed class NetworkResponse<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Success<T>(data: T?) : NetworkResponse<T>(data = data)
    class Failure<T>(data: T? = null, error: String?) : NetworkResponse<T>(data = data, error = error)
    class Loading<T>(val isLoading: Boolean ) : NetworkResponse<T>()
}