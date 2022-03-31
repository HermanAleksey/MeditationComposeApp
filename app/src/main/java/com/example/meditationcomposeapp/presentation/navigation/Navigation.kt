package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditationcomposeapp.presentation.main_screen.MainScreen
import com.example.meditationcomposeapp.presentation.screens.enter.EnterScreen
import com.example.meditationcomposeapp.presentation.screens.login.LoginScreen
import com.example.meditationcomposeapp.presentation.screens.newpassword.NewPasswordScreen
import com.example.meditationcomposeapp.presentation.screens.registration.RegistrationScreen
import com.example.meditationcomposeapp.presentation.screens.restorepassword.RestorePasswordScreen
import com.example.meditationcomposeapp.presentation.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.NewPassword.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Enter.route) {
            EnterScreen(
                viewModel = viewModel(),
                navigateToLoginScreen = { navController.navigate(Screen.Login.route) },
                navigateToRegistrationScreen = { navController.navigate(Screen.Registration.route) }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = viewModel(),
                navigateToMainScreen = {},
                navigateToRegistrationScreen = { navController.navigate(Screen.Registration.route) },
                navigateToRestorePasswordScreen = { navController.navigate(Screen.RestorePassword.route) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                viewModel = viewModel(),
                navigateToLoginScreen = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.RestorePassword.route) {
            RestorePasswordScreen(
                viewModel = viewModel(),
                navigateToNewPasswordScreen = { navController.navigate(Screen.NewPassword.route) }
            )
        }
        composable(Screen.NewPassword.route) {
            NewPasswordScreen(
                viewModel = viewModel(),
                navigateToLoginScreen = { navController.navigate(Screen.Login.route) }
            )
        }

        //TODO separate navigation of modules into different graphs; add integration with bottom nav bar in main screen
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}