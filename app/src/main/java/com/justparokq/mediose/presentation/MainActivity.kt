package com.justparokq.mediose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
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
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavDependenciesProvider
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.dialog.DialogController
import com.justparokq.core.design_system.dialog.MedioseDialogProvider
import com.justparokq.mediose.presentation.navigation.MeditationDestinationsNavHost
import com.justparokq.mediose.presentation.navigation.NavDependenciesProviderImpl
import com.justparokq.mediose.presentation.navigation.getDestinationWrapper
import com.justparokq.mediose.presentation.ui_controls.bottom_nav_bar.NavigationBottomBar
import com.justparokq.mediose.presentation.ui_controls.toolbar.MedioseToolbarProvider
import com.justparokq.mediose.presentation.ui_controls.toolbar.ToolbarViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), NavDependenciesProvider, DialogController {

    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var navController: NavHostController
    private var navDepProvider: NavDependenciesProvider? = null

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberAnimatedNavController()
            MyApp(
                navController = navController,
                viewModel = mainViewModel,
                dialogController = this as DialogController,
            )
        }
    }

    override fun show(dialogProvider: MedioseDialogProvider) {
        mainViewModel.onShowDialogRequested(dialogProvider)
    }

    override fun close() {
        mainViewModel.onCloseDialog()
    }

    override fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D {
        if (navDepProvider == null) {
            navDepProvider = NavDependenciesProviderImpl(navController)
        }

        return navDepProvider!!.provideDependencies(clazz)
    }
}

@Composable
fun MyApp(
    navController: NavHostController,
    viewModel: MainViewModel,
    dialogController: DialogController,
) {
    val systemUiController = rememberSystemUiController()

    val bottomBarIsVisible = viewModel.bottomBarIsVisible.collectAsState()
    val toolbarIsVisible = viewModel.toolbarIsVisible.collectAsState()
    val dialogIsVisible = viewModel.dialogIsVisible.collectAsState()

    val dialogProvider = viewModel.dialogProvider.collectAsState()
    val toolbarProvider = viewModel.toolbarProvider.collectAsState()

    val toolbarViewModel = hiltViewModel<ToolbarViewModel>()
    LaunchedEffect(key1 = Unit) {
        viewModel.onLaunch(
            initialToolbarProvider = MedioseToolbarProvider(
                viewModel = toolbarViewModel,
                dialogController = dialogController
            )
        )
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        destination.getDestinationWrapper()?.let {
            viewModel.onDestinationChanged(it)
        }
    }

    AppTheme(false) {
        systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
        Scaffold(
            topBar = {
                if (toolbarIsVisible.value)
                    toolbarProvider.value?.Display()
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarIsVisible.value,
                    enter = slideInVertically(initialOffsetY = { it }),
                ) {
                    NavigationBottomBar(navController)
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MeditationDestinationsNavHost(
                    navController = navController,
                )

                if (dialogIsVisible.value)
                    dialogProvider.value?.Display()
            }
        }
    }
}
