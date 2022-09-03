package com.example.meditationcomposeapp.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.EnterScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.EnterCodeScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login.EnterLoginScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.LoginScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.new_password.NewPasswordScreen
import com.example.meditationcomposeapp.presentation.screens.login_flow.registration.RegistrationScreen

fun NavGraphBuilder.authNavGraph(
    setStatusBarColor: (Int) -> Unit,
    navController: NavHostController
) {
    navigation(startDestination = Screen.Enter.route, Route.AUTHENTICATION.name) {
        composable(Screen.Enter.route) {
            EnterScreen(
                viewModel = hiltViewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToLoginScreen = { navController.navigateTo(Screen.Login) },
                navigateToRegistrationScreen = { navController.navigateTo(Screen.Registration) }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToMainScreen = { navController.navigateTo(Screen.Main) },
                navigateToEnterLoginScreen = { initialLogin: String ->
                    //TODO check best practice
                    navController.navigate(Screen.EnterLogin.route + "?login=$initialLogin")
//                    navigateTo(Screen.EnterLogin)
                },
                navigateToRegistrationScreen = { navController.navigateTo(Screen.Registration) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                viewModel = hiltViewModel(),
                setStatusBarColor = setStatusBarColor,
                navigateToLoginScreen = { navController.navigateTo(Screen.Login) }
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
                navigateToEnterCodeScreen = { navController.navigateTo(Screen.EnterCode) }
            )
        }
        composable(Screen.EnterCode.route) {
            EnterCodeScreen(
                viewModel = hiltViewModel(),
                navigateToNewPasswordScreen = { navController.navigateTo(Screen.NewPassword) }
            )
        }
        composable(Screen.NewPassword.route) {
            NewPasswordScreen(
                viewModel = hiltViewModel(),
                navigateToLoginScreen = { navController.navigateTo(Screen.Login) }
            )
        }
    }
}