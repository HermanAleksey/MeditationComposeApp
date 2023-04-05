package com.example.internet_connection

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class NoInternetConnectionNavDependencies(
    val navigateBack: () -> Unit,
) : NavDependencies

sealed class NoInternetConnectionNavRoute : NavRoute<NoInternetConnectionNavDependencies>() {

    object Back : NoInternetConnectionNavRoute() {
        override fun navigate(navDependencies: NoInternetConnectionNavDependencies) {
            navDependencies.navigateBack()
        }
    }
}
