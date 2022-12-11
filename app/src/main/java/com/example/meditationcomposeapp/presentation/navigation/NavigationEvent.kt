package com.example.meditationcomposeapp.presentation.navigation

import com.example.meditationcomposeapp.presentation.screens.destinations.DirectionDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

sealed class NavigationEvent {
    class Navigate(
        private val direction: Direction
    ) : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator) {
            navigator.navigate(direction = direction)
        }
    }

    class NavigateWithPop(
        private val direction: Direction,
        private val popUpTo: DirectionDestination,
        private val inclusive: Boolean,
    ) : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator) =
            this.let { navigationEvent ->
                navigator.navigate(direction = direction) {
                    popUpTo(navigationEvent.popUpTo.route) {
                        inclusive = navigationEvent.inclusive
                    }
                }
            }
    }

    class Pop(
        private val popUpTo: DirectionDestination,
        private val inclusive: Boolean,
    ) : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator): Unit =
            this.let { navigationEvent ->
                navigator.popBackStack(
                    route = navigationEvent.popUpTo,
                    inclusive = navigationEvent.inclusive
                )
            }
    }

    object Empty : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator) {
            //do nothing
        }
    }

    abstract fun handleNavigationEvent(navigator: DestinationsNavigator)
}