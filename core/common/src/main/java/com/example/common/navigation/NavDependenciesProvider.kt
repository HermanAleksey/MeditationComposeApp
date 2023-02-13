package com.example.common.navigation

interface NavDependenciesProvider {

    fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D
}