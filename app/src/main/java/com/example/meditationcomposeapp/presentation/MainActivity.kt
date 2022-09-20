package com.example.meditationcomposeapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.meditationcomposeapp.presentation.screens.NavGraphs
import com.example.meditationcomposeapp.presentation.screens.destinations.*
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter.EnterScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code.EnterCodeScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login.EnterLoginScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.login.LoginScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.new_password.NewPasswordScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.login_flow.registration.RegistrationScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.BeerListScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.main_flow.bottom_nav_bar.BottomBar
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.MainScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.splash.SplashScreenViewModel
import com.example.meditationcomposeapp.ui.theme.ColorBackground
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MyApp(window)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(windows: Window) {
    val navController = rememberNavController()
    val statusBarColor = ColorBackground.toArgb()

    fun setStatusBarColor(color: Int) {
        windows.statusBarColor = color
    }

    MeditationComposeAppTheme {
        windows.statusBarColor = statusBarColor
        windows.navigationBarColor = ColorBackground.toArgb()
        var bottomBarIsVisible by remember {
            mutableStateOf(false)
        }

        fun setBottomBarVisibility(isVisible: Boolean) {
            bottomBarIsVisible = isVisible
        }

        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarIsVisible,
                    enter = slideInVertically(
                        initialOffsetY = {
                            it
                        }
                    ) ,
                    exit = slideOutVertically(
                        targetOffsetY = {
                            it
                        }
                    ),
                ) {
                    BottomBar(navController = navController)
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                DestinationsNavHost(navGraph = NavGraphs.root, dependenciesContainerBuilder = {
                    dependency(SplashScreenDestination) { hiltViewModel<SplashScreenViewModel>() }
                    dependency(EnterScreenDestination) { hiltViewModel<EnterScreenViewModel>() }
                    dependency(EnterCodeScreenDestination) { hiltViewModel<EnterCodeScreenViewModel>() }
                    dependency(EnterLoginScreenDestination) { hiltViewModel<EnterLoginScreenViewModel>() }
                    dependency(LoginScreenDestination) { hiltViewModel<LoginScreenViewModel>() }
                    dependency(NewPasswordScreenDestination) { hiltViewModel<NewPasswordScreenViewModel>() }
                    dependency(RegistrationScreenDestination) { hiltViewModel<RegistrationScreenViewModel>() }

                    dependency(BeerListScreenDestination) { hiltViewModel<BeerListScreenViewModel>() }
                    dependency(MainScreenDestination) { hiltViewModel<MainScreenViewModel>() }
                })
            }
        }
    }
}