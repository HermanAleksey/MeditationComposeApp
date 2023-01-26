package com.example.common.mapper

interface Mapper<T, K> {
    fun mapFrom(objectFrom: K): T
}

interface BidirectionalMapper<T, K> : Mapper<T, K> {

    fun mapTo(objectFrom: T): K
}