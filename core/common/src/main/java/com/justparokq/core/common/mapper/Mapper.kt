package com.justparokq.core.common.mapper

interface Mapper<T, K> {
    fun mapFrom(objectFrom: K): T
}

interface BidirectionalMapper<T, K> : Mapper<T, K> {

    fun mapTo(objectFrom: T): K
}

interface SuspendableMapper<T, K> {
    suspend fun mapFrom(objectFrom: K): T
}

interface BidirectionalSuspendableMapper<T, K> : SuspendableMapper<T, K> {

    suspend fun mapTo(objectFrom: T): K
}