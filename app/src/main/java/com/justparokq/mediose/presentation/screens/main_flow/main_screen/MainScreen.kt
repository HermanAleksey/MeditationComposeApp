package com.justparokq.mediose.presentation.screens.main_flow.main_screen

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.justparokq.core.common.navigation.NavDependenciesProvider
import com.justparokq.feature.main.api.MainScreenNavDependencies
import com.justparokq.feature.main.api.MainScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
) {
    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(MainScreenNavDependencies::class.java)

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    com.justparokq.feature.main.api.MainScreen(viewModel = viewModel)
}
