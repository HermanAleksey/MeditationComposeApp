package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.authentication.internal.screens.enter.EnterScreenViewModel
import com.example.authentication.internal.screens.enter_code.EnterCodeScreenViewModel
import com.example.authentication.internal.screens.enter_login.EnterLoginScreenViewModel
import com.example.authentication.internal.screens.login.LoginScreenViewModel
import com.example.authentication.internal.screens.new_password.NewPasswordScreenViewModel
import com.example.authentication.internal.screens.registration.RegistrationScreenViewModel
import com.example.beer_sorts.api.DetailedBeerScreenViewModel
import com.example.beer_sorts.api.BeerListScreenViewModel
import com.example.feature.main.api.MainScreenViewModel
import com.example.feature.profile.api.ProfileScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.NavGraphs
import com.example.meditationcomposeapp.presentation.screens.destinations.*
import com.example.shuffle_puzzle.api.ShufflePuzzleScreenViewModel
import com.example.splash_screen.api.SplashScreenViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MeditationDestinationsNavHost(
    navController: NavHostController,
    screenWidth: Int
) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        navController = navController,
        engine = MeditationNavHostEngine(screenWidth),
        dependenciesContainerBuilder = {
            dependency(SplashScreenDestination) { hiltViewModel<SplashScreenViewModel>() }
            dependency(EnterScreenDestination) { hiltViewModel<EnterScreenViewModel>() }
            dependency(EnterCodeScreenDestination) { hiltViewModel<EnterCodeScreenViewModel>() }
            dependency(EnterLoginScreenDestination) { hiltViewModel<EnterLoginScreenViewModel>() }
            dependency(LoginScreenDestination) { hiltViewModel<LoginScreenViewModel>() }
            dependency(NewPasswordScreenDestination) { hiltViewModel<NewPasswordScreenViewModel>() }
            dependency(RegistrationScreenDestination) { hiltViewModel<RegistrationScreenViewModel>() }

            dependency(MainScreenDestination) { hiltViewModel<MainScreenViewModel>() }
            dependency(BeerListScreenDestination) { hiltViewModel<BeerListScreenViewModel>() }
            dependency(DetailedBeerScreenDestination) { hiltViewModel<DetailedBeerScreenViewModel>() }
            dependency(ShufflePuzzleScreenDestination) { hiltViewModel<ShufflePuzzleScreenViewModel>() }
            dependency(ProfileScreenDestination) { hiltViewModel<ProfileScreenViewModel>() }
        }
    )
}