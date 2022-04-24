package com.example.meditationcomposeapp.presentation

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.meditationcomposeapp.presentation.navigation.SetupNavGraph
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme
import com.example.meditationcomposeapp.ui.theme.ColorBackground
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

@Composable
fun MyApp(windows: Window) {
    val navController = rememberNavController()
    val statusBarColor = ColorBackground.toArgb()

    fun setStatusBarColor(color: Int){
        windows.statusBarColor = color
    }

    MeditationComposeAppTheme {
        windows.statusBarColor = statusBarColor
        windows.navigationBarColor = ColorBackground.toArgb()
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SetupNavGraph(::setStatusBarColor, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationComposeAppTheme {
    }
}