package com.justparokq.mediose.presentation.navigation

import androidx.navigation.NavDestination
import com.justparokq.mediose.presentation.screens.destinations.BeerListScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.DetailedBeerScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.EnterCodeScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.EnterLoginScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.EnterScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.FeatureToggleScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.LoginScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.MainScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.MusicScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.NewPasswordScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.NoInternetConnectionScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.RegistrationScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.ShufflePuzzleScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.TypedDestination
import com.justparokq.mediose.presentation.screens.destinations.UpdatesHistoryScreenDestination

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
