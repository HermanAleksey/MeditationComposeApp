package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.MainScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.EnterScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.LoginScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.new_password.NewPasswordScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.registration.RegistrationScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.restore_password.RestorePasswordScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.splash.SplashScreen

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
                viewModel = hiltViewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToLoginScreen = { navigateTo(Screen.Login) },
                navigateToRegistrationScreen = { navigateTo(Screen.Registration) }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToMainScreen = { navigateTo(Screen.Main) },
                navigateToRegistrationScreen = { navigateTo(Screen.Registration) },
                navigateToRestorePasswordScreen = { navigateTo(Screen.RestorePassword) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                viewModel = hiltViewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToLoginScreen = { navigateTo(Screen.Login) }
            )
        }
        composable(Screen.RestorePassword.route) {
            RestorePasswordScreen(
                viewModel = hiltViewModel(),
                navigateToNewPasswordScreen = { navigateTo(Screen.NewPassword) }
            )
        }
        composable(Screen.NewPassword.route) {
            NewPasswordScreen(
                viewModel = hiltViewModel(),
                navigateToLoginScreen = { navigateTo(Screen.Login) }
            )
        }

        //TODO separate navigation of modules into different graphs; add integration with bottom nav bar in main screen
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}