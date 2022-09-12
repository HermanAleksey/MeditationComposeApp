package com.example.meditationcomposeapp.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditationcomposeapp.presentation.navigation.Route
import com.example.meditationcomposeapp.presentation.navigation.Screen
import com.example.meditationcomposeapp.presentation.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(
    setStatusBarColor: (Int) -> Unit,
    setNavBarVisibility: (Boolean) -> Unit,
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
                setStatusBarColor = setStatusBarColor,
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
        homeNavGraph(setNavBarVisibility, navController)
    }
}

typealias navigateFunc = () -> Unit

fun NavController.navigateTo(screen: Screen) {
    this.navigate(screen.route) {
        if (screen.route == Screen.NewPassword.route)
            popUpTo(Screen.Login.route)
    }
}