package com.example.common.view_model

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

sealed class NavigationEvent {
    class Navigate(
        private val direction: Direction
    ) : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator) {
            navigator.navigate(direction = direction)
        }

        override fun toString(): String {
            return "Navigate{route=${this.direction.route}}"
        }
    }

    class NavigateWithPop(
        private val direction: Direction,
        //todo check if works DirectionDestination.Route
        private val popUpTo: String,
        private val inclusive: Boolean,
    ) : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator) =
            this.let { navigationEvent ->
                navigator.navigate(direction = direction) {
                    popUpTo(navigationEvent.popUpTo) {
                        inclusive = navigationEvent.inclusive
                    }
                }
            }

        override fun toString(): String {
            return "NavigateWithPop{route=${this.direction.route},popUpTo=${this.popUpTo},inclusive=${this.inclusive}}"
        }
    }

    class Pop(
//      todo check if works   private val popUpTo: DirectionDestination,
        private val popUpTo: String,
        private val inclusive: Boolean,
    ) : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator): Unit =
            this.let { navigationEvent ->
                navigator.popBackStack(
                    route = navigationEvent.popUpTo,
                    inclusive = navigationEvent.inclusive
                )
            }

        override fun toString(): String {
            return "Pop{popUpTo=${this.popUpTo},inclusive=${this.inclusive}}"
        }
    }

    object Empty : NavigationEvent() {

        override fun handleNavigationEvent(navigator: DestinationsNavigator) {
            //do nothing
        }

        override fun toString(): String {
            return "NavigationEvent.Empty"
        }
    }

    abstract fun handleNavigationEvent(navigator: DestinationsNavigator)
}