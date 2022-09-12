package com.example.meditationcomposeapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.meditationcomposeapp.presentation.navigation.graph.SetupNavGraph
import com.example.meditationcomposeapp.presentation.screens.main_flow.bottom_nav_bar.BottomBar
import com.example.meditationcomposeapp.ui.theme.ColorBackground
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme
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
        ) { innerPaddings ->
            SetupNavGraph(
                ::setStatusBarColor,
                ::setBottomBarVisibility,
                navController = navController,
                //todo pass paddings and set them [innerPaddings]
            )
        }
    }
}