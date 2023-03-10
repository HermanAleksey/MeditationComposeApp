package com.example.common.navigation

import java.util.concurrent.atomic.AtomicBoolean

abstract class NavRoute<D : NavDependencies> {

    private var navigationHasBeenHandled: AtomicBoolean = AtomicBoolean(false)

    fun tryNavigate(navDependencies: D) {
        return if (navigationHasBeenHandled.get()) {
            return
        } else {
            navigationHasBeenHandled.set(true)
            navigate(navDependencies)
            navigationHasBeenHandled.set(false)
        }
    }

    protected abstract fun navigate(navDependencies: D)

}