package com.justparokq.mediose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.authentication.api.enter_code_screen.EnterCodeScreenViewModel
import com.example.authentication.api.enter_login_screen.EnterLoginScreenViewModel
import com.example.authentication.api.enter_screen.EnterScreenViewModel
import com.example.authentication.api.login_screen.LoginScreenViewModel
import com.example.authentication.api.new_password_screen.NewPasswordScreenViewModel
import com.example.authentication.api.registration_screen.RegistrationScreenViewModel
import com.example.beer_sorts.api.beer_list.BeerListScreenViewModel
import com.example.beer_sorts.api.detailed_beer.DetailedBeerScreenViewModel
import com.example.feature.main.api.MainScreenViewModel
import com.example.feature.music_player.ui.viewmodels.MusicScreenViewModel
import com.example.feature.update_history.api.UpdatesDescriptionViewModel
import com.example.feature_toggle.api.FeatureToggleScreenViewModel
import com.example.internet_connection.NoInternetConnectionViewModel
import com.justparokq.mediose.presentation.screens.NavGraphs
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
import com.justparokq.mediose.presentation.screens.destinations.SplashScreenDestination
import com.justparokq.mediose.presentation.screens.destinations.UpdatesHistoryScreenDestination
import com.example.shuffle_puzzle.api.ShufflePuzzleScreenViewModel
import com.example.splash_screen.api.SplashScreenViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MeditationDestinationsNavHost(
    navController: NavHostController,
) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        navController = navController,
        engine = MeditationNavHostEngine(),
        dependenciesContainerBuilder = {
            dependency(NoInternetConnectionScreenDestination) {
                hiltViewModel<NoInternetConnectionViewModel>()
            }
            dependency(SplashScreenDestination) { hiltViewModel<SplashScreenViewModel>() }

            dependency(EnterScreenDestination) { hiltViewModel<EnterScreenViewModel>() }
            dependency(EnterCodeScreenDestination) { hiltViewModel<EnterCodeScreenViewModel>() }
            dependency(EnterLoginScreenDestination) { hiltViewModel<EnterLoginScreenViewModel>() }
            dependency(LoginScreenDestination) { hiltViewModel<LoginScreenViewModel>() }
            dependency(NewPasswordScreenDestination) { hiltViewModel<NewPasswordScreenViewModel>() }
            dependency(RegistrationScreenDestination) {
                hiltViewModel<RegistrationScreenViewModel>()
            }

            dependency(MainScreenDestination) { hiltViewModel<MainScreenViewModel>() }
            dependency(BeerListScreenDestination) { hiltViewModel<BeerListScreenViewModel>() }
            dependency(DetailedBeerScreenDestination) {
                hiltViewModel<DetailedBeerScreenViewModel>()
            }
            dependency(ShufflePuzzleScreenDestination) {
                hiltViewModel<ShufflePuzzleScreenViewModel>()
            }
            dependency(UpdatesHistoryScreenDestination) {
                hiltViewModel<UpdatesDescriptionViewModel>()
            }
            dependency(MusicScreenDestination) {
                hiltViewModel<MusicScreenViewModel>()
            }
            dependency(FeatureToggleScreenDestination) {
                hiltViewModel<FeatureToggleScreenViewModel>()
            }
        }
    )
}
