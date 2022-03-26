package com.example.meditationcomposeapp.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditationcomposeapp.presentation.navigation.SetupNavGraph
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
        installSplashScreen()
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
            SetupNavGraph(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationComposeAppTheme {
    }
}