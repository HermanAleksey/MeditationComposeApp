package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.meditationcomposeapp.presentation.screens.NavGraphs
import com.example.meditationcomposeapp.presentation.screens.destinations.*
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.EnterScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.EnterCodeScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login.EnterLoginScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.LoginScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.new_password.NewPasswordScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.registration.RegistrationScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.BeerListScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.MainScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle.ShufflePuzzleScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen.ProfileScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.splash.SplashScreenViewModel
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
            dependency(ShufflePuzzleScreenDestination) { hiltViewModel<ShufflePuzzleScreenViewModel>() }
            dependency(TestScreenDestination) { hiltViewModel<ProfileScreenViewModel>() }
        }
    )
}