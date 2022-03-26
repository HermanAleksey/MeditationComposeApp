package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditationcomposeapp.presentation.screens.enter.EnterScreen
import com.example.meditationcomposeapp.presentation.screens.enter.EnterScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login.LoginScreen
import com.example.meditationcomposeapp.presentation.screens.login.LoginScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.registration.RegistrationScreen
import com.example.meditationcomposeapp.presentation.screens.registration.RegistrationScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.restorepassword.RestorePasswordScreen
import com.example.meditationcomposeapp.presentation.screens.restorepassword.RestorePasswordScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Enter.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Enter.route) {
            EnterScreen(
                viewModel = EnterScreenViewModel(),
                navController
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = LoginScreenViewModel(),
                navController
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                viewModel = RegistrationScreenViewModel(),
                navController
            )
        }
        composable(Screen.RestorePassword.route) {
            RestorePasswordScreen(
                viewModel = RestorePasswordScreenViewModel(),
                navController
            )
        }
    }
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Splash.route
//    ) {
//        composable(route = Screen.Splash.route) {
//            AnimatedSplashScreen(navController = navController)
//        }
//        composable(route = Screen.Home.route) {
//            Box(modifier = Modifier.fillMaxSize())
//        }
//    }
}