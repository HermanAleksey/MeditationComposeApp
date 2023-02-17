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
import androidx.navigation.NavHostController
import com.example.authentication.api.enter_code_screen.EnterCodeScreenNavDependencies
import com.example.authentication.api.enter_login_screen.EnterLoginScreenNavDependencies
import com.example.authentication.api.enter_screen.EnterScreenNavDependencies
import com.example.authentication.api.login_screen.LoginScreenNavDependencies
import com.example.authentication.api.new_password_screen.NewPasswordScreenNavDependencies
import com.example.authentication.api.registration_screen.RegistrationScreenNavDependencies
import com.example.beer_sorts.api.BeerListNavDependencies
import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavDependenciesProvider
import com.example.design_system.AppTheme
import com.example.meditationcomposeapp.presentation.navigation.MeditationDestinationsNavHost
import com.example.meditationcomposeapp.presentation.navigation.getDestinationWrapper
import com.example.meditationcomposeapp.presentation.screens.destinations.*
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
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(), NavDependenciesProvider {

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberAnimatedNavController()
            MyApp(window, navController)
        }
    }

    override fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D {
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
                        navController.navigate(direction = NewPasswordScreenDestination(login)) {
                            popUpTo(LoginScreenDestination) {
                                inclusive = false
                            }
                        }
                    }
                )
            }
            EnterLoginScreenNavDependencies::class.java.name -> {
                EnterLoginScreenNavDependencies(
                    navigateEnterCodeScreen = { login ->
//                        navController.navigate(EnterCodeScreenDestination(login))
                    }
                )
            }
            LoginScreenNavDependencies::class.java.name -> {
                LoginScreenNavDependencies(
                    navigateToMainScreen = {
                        //todo add
                    },
                    navigateToRegistrationScreen = {
                        navController.navigate(RegistrationScreenDestination())
                    },
                    navigateToEnterLoginScreen = { login ->
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
                        navController.navigate(LoginScreenDestination())
                    }
                )
            }
            else -> throw java.lang.NullPointerException(":(")
        }
        return dependencies as D
        //todo update

    }
}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(
    windows: Window,
    navController: NavHostController,
) {
    val displayMetrics = DisplayMetrics()
    windows.windowManager.defaultDisplay.getMetrics(displayMetrics)
    val screenWidth = displayMetrics.widthPixels


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
                    screenWidth = screenWidth
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