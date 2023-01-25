package com.example.common.view_model

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class Event<out NavigationEvent>(private var content: NavigationEvent) {
    private var navigationHasBeenHandled: Boolean = false

    fun getNavigationIfNotHandled(): NavigationEvent? {
        return if (navigationHasBeenHandled) {
            return null
        } else {
            navigationHasBeenHandled = true
            content
        }
    }
}

fun Event<NavigationEvent>.processEvent(navigator: DestinationsNavigator) {
    getNavigationIfNotHandled()?.handleNavigationEvent(navigator)
}