package com.example.meditationcomposeapp.presentation.screens.login_flow

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.authentication.api.enter_login_screen.EnterLoginScreenNavDependencies
import com.example.authentication.api.enter_login_screen.EnterLoginScreenViewModel
import com.example.common.navigation.NavDependenciesProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EnterLoginScreen(
    initialLoginValue: String?,
    viewModel: EnterLoginScreenViewModel,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(EnterLoginScreenNavDependencies::class.java)

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    com.example.authentication.api.enter_login_screen.EnterLoginScreen(
        initialLoginValue = initialLoginValue,
        viewModel = viewModel,
    )
}