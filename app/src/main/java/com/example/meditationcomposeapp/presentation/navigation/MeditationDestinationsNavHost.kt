package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.authentication.api.enter_screen.EnterScreenViewModel
import com.example.authentication.api.enter_code_screen.EnterCodeScreenViewModel
import com.example.authentication.api.enter_login_screen.EnterLoginScreenViewModel
import com.example.authentication.api.login_screen.LoginScreenViewModel
import com.example.authentication.api.new_password_screen.NewPasswordScreenViewModel
import com.example.authentication.api.registration_screen.RegistrationScreenViewModel
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