package com.example.common.navigation

interface NavRoute<D : NavDependencies> {
    var navigationHasBeenHandled: Boolean

    fun navigate(navDependencies: D)

    fun tryNavigate(navDependencies: D)

    object Empty : NavRoute<NavDependencies> {
        override var navigationHasBeenHandled: Boolean
            get() = false
            set(value) {}

        override fun tryNavigate(navDependencies: NavDependencies) {
            //do nothing
        }

        override fun navigate(navDependencies: NavDependencies) {
            //do nothing
        }
    }
}