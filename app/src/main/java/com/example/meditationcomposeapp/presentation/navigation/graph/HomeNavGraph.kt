package com.example.meditationcomposeapp.presentation.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.meditationcomposeapp.presentation.navigation.Route
import com.example.meditationcomposeapp.presentation.navigation.Screen
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.BeerListScreen
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.MainScreen
import com.example.meditationcomposeapp.presentation.screens.main_flow.test_screens.screen3

fun NavGraphBuilder.homeNavGraph(
    setNavBarVisibility: (Boolean) -> Unit,
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Main.route,
        route = Route.MAIN.name
    ) {
        composable(Screen.Main.route) {
            setNavBarVisibility(true)
            MainScreen(hiltViewModel(), navController)
        }
        composable(Screen.BeerList.route) {
            BeerListScreen(hiltViewModel())
        }
        composable(Screen.Screen3.route) {
            screen3(
                viewModel = hiltViewModel(),
                navigateToEnterScreen = {
                    navController.navigateTo(Screen.Enter)
                }
            )
        }
    }
}