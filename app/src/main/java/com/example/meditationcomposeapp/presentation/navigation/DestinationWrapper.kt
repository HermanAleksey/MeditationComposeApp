package com.example.meditationcomposeapp.presentation.navigation

import androidx.navigation.NavDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.*


enum class DestinationWrapper(
    val destination: TypedDestination<*>,
    val toolbarVisible: Boolean = false,
    val bottomBarVisible: Boolean = false,
) {
//    EnterScreen(EnterScreenDestination),
//    EnterCodeScreen(EnterCodeScreenDestination),
//    EnterLoginScreen(EnterLoginScreenDestination),
//    LoginScreen(LoginScreenDestination),
//    NewPasswordScreen(NewPasswordScreenDestination),
//    RegistrationScreen(RegistrationScreenDestination),

    BeerListScreen(BeerListScreenDestination, true, true),
    DetailedBeerScreen(DetailedBeerScreenDestination, true, true),
    MainScreen(MainScreenDestination, true, true),
    ShufflePuzzleScreen(ShufflePuzzleScreenDestination, true, true),
    ProfileScreen(TestScreenDestination, true, true),
}

fun <T> TypedDestination<T>.getDestinationWrapper() =
    DestinationWrapper.values().find {
        it.destination == this
    }

fun NavDestination.getDestinationWrapper() =
    DestinationWrapper.values().find {
        this.route?.startsWith(it.destination.route) ?: false
    }