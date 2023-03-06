package com.example.common.navigation

import android.util.Log

abstract class NavRoute<D : NavDependencies> {

    private var navigationHasBeenHandled: Boolean = false

    fun tryNavigate(navDependencies: D) {
        Log.e("TAGG", "tryNavigate: $navigationHasBeenHandled")
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    fun releaseNavigationLock() {
        navigationHasBeenHandled = false
    }

    protected abstract fun navigate(navDependencies: D)

}