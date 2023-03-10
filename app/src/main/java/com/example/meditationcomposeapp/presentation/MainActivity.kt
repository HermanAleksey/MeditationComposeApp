package com.example.meditationcomposeapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.navigation.NavHostController
import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavDependenciesProvider
import com.example.design_system.AppTheme
import com.example.meditationcomposeapp.presentation.navigation.MeditationDestinationsNavHost
import com.example.meditationcomposeapp.presentation.navigation.NavDependenciesProviderImpl
import com.example.meditationcomposeapp.presentation.navigation.getDestinationWrapper
import com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar.BottomBar
import com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar.BottomBarController
import com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar.BottomBarState
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.DialogController
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.DialogType
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.MeditationDialog
import com.example.meditationcomposeapp.presentation.ui_controls.toolbar.ToolBarController
import com.example.meditationcomposeapp.presentation.ui_controls.toolbar.Toolbar
import com.example.meditationcomposeapp.presentation.ui_controls.toolbar.ToolbarState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(), NavDependenciesProvider {

    private lateinit var navController: NavHostController
    private var navDepProvider: NavDependenciesProvider? = null

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberAnimatedNavController()
            MyApp(navController)
        }
    }

    override fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D {
        if (navDepProvider == null) {
            navDepProvider = NavDependenciesProviderImpl(navController)
        }

        return navDepProvider!!.provideDependencies(clazz)
    }
}

@Suppress("LocalVariableName")
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(
    navController: NavHostController,
) {
    val systemUiController = rememberSystemUiController()

    var _bottomBarIsVisible by remember {
        mutableStateOf(false)
    }
    var _bottomBarState: BottomBarState by remember {
        mutableStateOf(BottomBarState.NavigationBottomBarState(navController))
    }
    val bottomBarController = object : BottomBarController {
        override fun setState(state: BottomBarState) {
            _bottomBarState = state
        }

        override fun show() {
            _bottomBarIsVisible = true
        }

        override fun hide() {
            _bottomBarIsVisible = false
        }
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

    var _toolbarIsVisible by remember {
        mutableStateOf(false)
    }
    var _toolbarState: ToolbarState by remember {
        mutableStateOf(ToolbarState.ToolbarMainState(dialogController))
    }
    val toolBarController = object : ToolBarController {
        override fun setState(state: ToolbarState) {
            _toolbarState = state
        }

        override fun show() {
            _toolbarIsVisible = true
        }

        override fun hide() {
            _toolbarIsVisible = false
        }
    }

    navController.addOnDestinationChangedListener { controller, destination, args ->
        destination.getDestinationWrapper()?.let {
            val toolbarShouldBeVisible = it.toolbarVisible
            val bottomBarShouldBeVisible = it.bottomBarVisible

            if (toolbarShouldBeVisible)
                toolBarController.show()
            else toolBarController.hide()

            if (bottomBarShouldBeVisible)
                bottomBarController.show()
            else bottomBarController.hide()
        }
    }

    AppTheme(false) {
        systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
//        ModalBottomSheetLayout(sheetContent = {
//
//        }) {
        Scaffold(
            topBar = {
                if (_toolbarIsVisible)
                    Toolbar(
                        toolbarState = _toolbarState,
                        hiltViewModel(),
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
                    BottomBar(state = _bottomBarState)
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MeditationDestinationsNavHost(
                    navController = navController,
                )

                if (_dialogIsVisible)
                    MeditationDialog(
                        onDismissRequest = { _dialogIsVisible = false },
                        dialogType = _dialogType
                    )
            }
        }
//        }
    }
}