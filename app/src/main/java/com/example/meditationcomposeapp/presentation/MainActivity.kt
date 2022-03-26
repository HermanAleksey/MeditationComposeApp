package com.example.meditationcomposeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditationcomposeapp.presentation.screens.enter.EnterScreen
import com.example.meditationcomposeapp.presentation.screens.enter.EnterScreenViewModel
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme
import com.example.meditationcomposeapp.presentation.screens.login.LoginScreen
import com.example.meditationcomposeapp.presentation.screens.login.LoginScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.registration.RegistrationScreen
import com.example.meditationcomposeapp.presentation.screens.registration.RegistrationScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.restorepassword.RestorePasswordScreen
import com.example.meditationcomposeapp.presentation.screens.restorepassword.RestorePasswordScreenViewModel
import com.example.meditationcomposeapp.presentation.screens.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    MeditationComposeAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(navController = navController, startDestination = Screen.ENTER) {
                composable(Screen.SPLASH) {
                    SplashScreen()
                }
                composable(Screen.ENTER) {
                    EnterScreen(
                        viewModel = EnterScreenViewModel(),
                        navController
                    )
                }
                composable(Screen.LOGIN) {
                    LoginScreen(
                        viewModel = LoginScreenViewModel(),
                        navController
                    )
                }
                composable(Screen.REGISTRATION) {
                    RegistrationScreen(
                        viewModel = RegistrationScreenViewModel(),
                        navController
                    )
                }
                composable(Screen.RESTORE_PASSWORD) {
                    RestorePasswordScreen(
                        viewModel = RestorePasswordScreenViewModel(),
                        navController
                    )
                }
            }
        }
    }
}

object Screen {
    const val SPLASH = "com.example.meditationcomposeapp.screens.SPLASH_SCREEN"
    const val ENTER = "com.example.meditationcomposeapp.screens.ENTER_SCREEN"
    const val LOGIN = "com.example.meditationcomposeapp.screens.LOGIN_SCREEN"
    const val REGISTRATION = "com.example.meditationcomposeapp.screens.REGISTRATION_SCREEN"
    const val RESTORE_PASSWORD = "com.example.meditationcomposeapp.screens.RESTORE_PASSWORD_SCREEN"
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationComposeAppTheme {
    }
}