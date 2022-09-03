package com.example.meditationcomposeapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.MainScreen

fun NavGraphBuilder.homeNagGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.Main.route,
        route = Route.MAIN.name
    ){
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}