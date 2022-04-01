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
fun SetupNavGraph(
    setStatusBarColor: (Int) -> Unit,
    navController: NavHostController
) {

    fun navigateTo(screen: Screen){
        navController.navigate(screen.route)
    }

    NavHost(navController = navController, startDestination = Screen.Enter.route) {
        composable(Screen.Splash.route) {
            SplashScreen()
        }
        composable(Screen.Enter.route) {
            EnterScreen(
                viewModel = viewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToLoginScreen = { navigateTo(Screen.Login) },
                navigateToRegistrationScreen = { navigateTo(Screen.Registration) }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = viewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToMainScreen = { navigateTo(Screen.Main) },
                navigateToRegistrationScreen = { navigateTo(Screen.Registration) },
                navigateToRestorePasswordScreen = { navigateTo(Screen.RestorePassword) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                viewModel = viewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToLoginScreen = { navigateTo(Screen.Login) }
            )
        }
        composable(Screen.RestorePassword.route) {
            RestorePasswordScreen(
                viewModel = viewModel(),
                navigateToNewPasswordScreen = { navigateTo(Screen.NewPassword) }
            )
        }
        composable(Screen.NewPassword.route) {
            NewPasswordScreen(
                viewModel = viewModel(),
                navigateToLoginScreen = { navigateTo(Screen.Login) }
            )
        }

        //TODO separate navigation of modules into different graphs; add integration with bottom nav bar in main screen
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}