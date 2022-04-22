package com.example.meditationcomposeapp.data_source.mappers

interface Mapper<T,K>{
    fun mapFrom(objectFrom: K): T
}