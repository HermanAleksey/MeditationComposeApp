package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.MainScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.EnterScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login.EnterLoginScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.LoginScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.new_password.NewPasswordScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.registration.RegistrationScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.EnterCodeScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.splash.SplashScreen

@Composable
fun SetupNavGraph(
    setStatusBarColor: (Int) -> Unit,
    navController: NavHostController
) {

    fun navigateTo(screen: Screen) {
        navController.navigate(screen.route) {
            if (screen.route == Screen.NewPassword.route)
                popUpTo(Screen.Login.route)
        }
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
                navigateToEnterLoginScreen = { initialLogin: String ->
                    //TODO check best practice
                    navController.navigate(Screen.EnterLogin.route+"?login=$initialLogin")
//                    navigateTo(Screen.EnterLogin)
                },
                navigateToRegistrationScreen = { navigateTo(Screen.Registration) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                viewModel = hiltViewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToLoginScreen = { navigateTo(Screen.Login) }
            )
        }
        composable(
            route = Screen.EnterLogin.route + "?" + "login={login}",
            arguments = listOf(
                navArgument("{login}") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            EnterLoginScreen(
                backStackEntry.arguments?.getString("login"),
                viewModel = hiltViewModel(),
                navigateToEnterCodeScreen = { navigateTo(Screen.EnterCode) }
            )
        }
        composable(Screen.EnterCode.route) {
            EnterCodeScreen(
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