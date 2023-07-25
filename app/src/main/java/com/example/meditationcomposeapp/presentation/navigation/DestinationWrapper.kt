package com.example.meditationcomposeapp.presentation.navigation

import androidx.navigation.NavDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.BeerListScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.DetailedBeerScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterCodeScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterLoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.FeatureToggleScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MusicScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.NewPasswordScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.NoInternetConnectionScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.RegistrationScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.ShufflePuzzleScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.TypedDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.UpdatesHistoryScreenDestination

enum class DestinationWrapper(
    val destination: TypedDestination<*>,
    val toolbarVisible: Boolean = false,
    val bottomBarVisible: Boolean = false,
) {
    NoInternetConnectionScreen(NoInternetConnectionScreenDestination),

    EnterScreen(EnterScreenDestination),
    EnterCodeScreen(EnterCodeScreenDestination),
    EnterLoginScreen(EnterLoginScreenDestination),
    LoginScreen(LoginScreenDestination),
    NewPasswordScreen(NewPasswordScreenDestination),
    RegistrationScreen(RegistrationScreenDestination),

    BeerListScreen(BeerListScreenDestination, true, true),
    DetailedBeerScreen(DetailedBeerScreenDestination, true, false),
    MainScreen(MainScreenDestination, true, true),
    ShufflePuzzleScreen(ShufflePuzzleScreenDestination, true, true),
    UpdateHistoryScreen(UpdatesHistoryScreenDestination, true, false),
    MusicScreen(MusicScreenDestination, true, false),
    FeatureToggleScreen(FeatureToggleScreenDestination, false, false),
}

fun NavDestination.getDestinationWrapper() =
    DestinationWrapper.values().find {
        this.route?.startsWith(it.destination.route) ?: false
    }
