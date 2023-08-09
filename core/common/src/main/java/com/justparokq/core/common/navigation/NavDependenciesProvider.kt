package com.justparokq.core.common.navigation

interface NavDependenciesProvider {

    fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D
}