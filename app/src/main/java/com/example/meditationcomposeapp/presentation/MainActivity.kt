package com.example.meditationcomposeapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationcomposeapp.presentation.navigation.MeditationDestinationsNavHost
import com.example.meditationcomposeapp.presentation.navigation.getDestinationWrapper
import com.example.meditationcomposeapp.presentation.screens.destinations.*
import com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar.BottomBar
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.DialogController
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.DialogType
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.MeditationDialog
import com.example.meditationcomposeapp.presentation.ui_controls.toolbar.Toolbar
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(windows: Window) {
    val displayMetrics = DisplayMetrics()
    windows.windowManager.defaultDisplay.getMetrics(displayMetrics)
    val screenWidth = displayMetrics.widthPixels

    val navController = rememberAnimatedNavController()
    val systemUiController = rememberSystemUiController()


    var _bottomBarIsVisible by remember {
        mutableStateOf(false)
    }
    var _toolbarIsVisible by remember {
        mutableStateOf(false)
    }
    var _dialogIsVisible by remember {
        mutableStateOf(false)
    }
    var _dialogType: DialogType by remember {
        mutableStateOf(
            DialogType.EmptyDialog
        )
    }

    val dialogController = object : DialogController {
        override fun show(dialogType: DialogType) {
            _dialogType = dialogType
            _dialogIsVisible = true
        }

        override fun close() {
            _dialogIsVisible = false
        }
    }

    navController.addOnDestinationChangedListener { controller, destination, args ->
        destination.getDestinationWrapper()?.let {
            val toolbarShouldBeVisible = it.toolbarVisible
            val bottomBarShouldBeVisible = it.bottomBarVisible
            if (_toolbarIsVisible != toolbarShouldBeVisible) {
                _toolbarIsVisible = toolbarShouldBeVisible
            }
            if (_bottomBarIsVisible != bottomBarShouldBeVisible) {
                _bottomBarIsVisible = toolbarShouldBeVisible
            }
        }
    }

    MeditationComposeAppTheme(false) {
        systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
        Scaffold(
            topBar = {
                if (_toolbarIsVisible)
                    Toolbar(
                        hiltViewModel(),
                        dialogController
                    )
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = _bottomBarIsVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it }
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { it }
                    ),
                ) {
                    BottomBar(navController = navController)
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MeditationDestinationsNavHost(
                    navController = navController,
                    screenWidth = screenWidth
                )

                if (_dialogIsVisible)
                    MeditationDialog(
                        onDismissRequest = { _dialogIsVisible = false },
                        dialogType = _dialogType
                    )
            }
        }
    }
}