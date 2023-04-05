package com.example.meditationcomposeapp.presentation.navigation

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
import com.example.feature.update_history.api.UpdatesDescriptionViewModel
import com.example.internet_connection.NoInternetConnectionViewModel
import com.example.meditationcomposeapp.presentation.screens.NavGraphs
import com.example.meditationcomposeapp.presentation.screens.destinations.BeerListScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.DetailedBeerScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterCodeScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterLoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.NewPasswordScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.NoInternetConnectionScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.RegistrationScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.ShufflePuzzleScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.SplashScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.UpdatesHistoryScreenDestination
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
        }
    )
}
