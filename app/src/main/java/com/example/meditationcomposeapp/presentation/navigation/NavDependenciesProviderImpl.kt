package com.example.meditationcomposeapp.presentation.navigation

import androidx.navigation.NavHostController
import com.example.authentication.api.enter_code_screen.EnterCodeScreenNavDependencies
import com.example.authentication.api.enter_login_screen.EnterLoginScreenNavDependencies
import com.example.authentication.api.enter_screen.EnterScreenNavDependencies
import com.example.authentication.api.login_screen.LoginScreenNavDependencies
import com.example.authentication.api.new_password_screen.NewPasswordScreenNavDependencies
import com.example.authentication.api.registration_screen.RegistrationScreenNavDependencies
import com.example.beer_sorts.api.beer_list.BeerListNavDependencies
import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavDependenciesProvider
import com.example.feature.main.api.MainScreenNavDependencies
import com.example.internet_connection.NoInternetConnectionNavDependencies
import com.example.meditationcomposeapp.presentation.screens.destinations.*
import com.example.meditationcomposeapp.presentation.ui_controls.toolbar.ToolbarNavDependencies
import com.example.splash_screen.api.SplashScreenNavDependencies
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo

class NavDependenciesProviderImpl(
    private val navController: NavHostController,
) : NavDependenciesProvider {

    override fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D {
        return findAuthFlowNavDependencies(clazz)
            ?: findMainFlowNavDependencies(clazz)
            ?: findBeerSortFlowNavDependencies(clazz)
            ?: findOtherScreensNavDependencies(clazz)
            ?: throw NotImplementedError(
                "NavDependencies not implemented for class ${clazz.canonicalName}"
            )
    }

    private fun <D : NavDependencies> findMainFlowNavDependencies(clazz: Class<D>): D? {
        val dependencies = when (clazz.name) {
            MainScreenNavDependencies::class.java.name -> {
                MainScreenNavDependencies(
                    navigateToPuzzleScreen = {
                        navController.navigate(ShufflePuzzleScreenDestination())
                    },
                )
            }
            ToolbarNavDependencies::class.java.name -> {
                ToolbarNavDependencies(
                    navigateToUpdatesHistory = {
                        if (navController.currentDestination?.route
                            == UpdatesHistoryScreenDestination.route
                        )
                            return@ToolbarNavDependencies

                        navController.navigate(UpdatesHistoryScreenDestination()) {
                            popUpTo(UpdatesHistoryScreenDestination) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToEnterScreen = {
                        navController.navigate(
                            EnterScreenDestination()
                        ) {
                            popUpTo(EnterScreenDestination)
                        }
                    }
                )
            }
            else -> null
        }
        @Suppress("UNCHECKED_CAST")
        return dependencies as D?
    }

    private fun <D : NavDependencies> findOtherScreensNavDependencies(clazz: Class<D>): D? {
        val dependencies = when (clazz.name) {
            SplashScreenNavDependencies::class.java.name -> {
                SplashScreenNavDependencies(
                    navigateToMainScreen = {
                        navController.navigate(
                            MainScreenDestination()
                        )
                    },
                    navigateToEnterScreen = {
                        navController.navigate(
                            EnterScreenDestination()
                        )
                    }
                )
            }
            NoInternetConnectionScreenDestination::class.java.name -> {
                NoInternetConnectionNavDependencies(
                    navigateBack = {
                        navController.popBackStack()
                        navController.popBackStack()
                    }
                )
            }
            else -> null
        }
        @Suppress("UNCHECKED_CAST")
        return dependencies as D?
    }

    private fun <D : NavDependencies> findBeerSortFlowNavDependencies(clazz: Class<D>): D? {
        val dependencies = when (clazz.name) {
            BeerListNavDependencies::class.java.name -> {
                BeerListNavDependencies(
                    navigateToBeerDetails = { beerId ->
                        navController.navigate(
                            DetailedBeerScreenDestination(beerId)
                        )
                    }
                )
            }
            else -> null
        }
        @Suppress("UNCHECKED_CAST")
        return dependencies as D?
    }

    private fun <D : NavDependencies> findAuthFlowNavDependencies(clazz: Class<D>): D? {
        val dependencies = when (clazz.name) {
            EnterScreenNavDependencies::class.java.name -> {
                EnterScreenNavDependencies(
                    navigateToLoginScreen = {
                        navController.navigate(LoginScreenDestination())
                    },
                    navigateToRegistrationScreen = {
                        navController.navigate(RegistrationScreenDestination())
                    }
                )
            }
            EnterCodeScreenNavDependencies::class.java.name -> {
                EnterCodeScreenNavDependencies(
                    navigateToNewPasswordScreen = { login ->
                        navController.navigate(
                            direction = NewPasswordScreenDestination(login)
                        ) {
                            popUpTo(LoginScreenDestination)
                        }
                    }
                )
            }
            EnterLoginScreenNavDependencies::class.java.name -> {
                EnterLoginScreenNavDependencies(
                    navigateEnterCodeScreen = { login ->
                        navController.navigate(EnterCodeScreenDestination(login))
                    }
                )
            }
            LoginScreenNavDependencies::class.java.name -> {
                LoginScreenNavDependencies(
                    navigateToMainScreen = {
                        navController.navigate(MainScreenDestination())
                    },
                    navigateToRegistrationScreen = {
                        navController.navigate(
                            direction = RegistrationScreenDestination()
                        ) {
                            popUpTo(EnterScreenDestination)
                        }
                    },
                    navigateToEnterCodeScreen = { login ->
                        navController.navigate(EnterCodeScreenDestination(login))
                    }
                )
            }
            NewPasswordScreenNavDependencies::class.java.name -> {
                NewPasswordScreenNavDependencies(
                    navigateToLoginScreen = {
                        navController.navigate(LoginScreenDestination())
                    }
                )
            }
            RegistrationScreenNavDependencies::class.java.name -> {
                RegistrationScreenNavDependencies(
                    navigateToLoginScreen = {
                        navController.navigate(LoginScreenDestination()) {
                            popUpTo(EnterScreenDestination)
                        }
                    }
                )
            }
            else -> null
        }
        @Suppress("UNCHECKED_CAST")
        return dependencies as D?
    }
}
