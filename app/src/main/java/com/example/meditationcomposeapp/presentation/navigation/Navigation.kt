package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditationcomposeapp.presentation.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(
    setStatusBarColor: (Int) -> Unit,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        route = Route.ROOT.name
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                viewModel = hiltViewModel(),
                navigateToEnterScreen = {
                    navController.navigate(Screen.Enter.route) {
                            popUpTo(Screen.Splash.route){
                                inclusive = true
                            }
                    }
                },
                navigateToHomeScreen = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        authNavGraph(setStatusBarColor, navController)
        homeNagGraph(navController)
    }
}

typealias navigateFunc = () -> Unit

fun NavController.navigateTo(screen: Screen) {
    this.navigate(screen.route) {
        if (screen.route == Screen.NewPassword.route)
            popUpTo(Screen.Login.route)
    }
}