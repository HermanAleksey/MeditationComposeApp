package com.example.meditationcomposeapp.presentation.screens.login_flow

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.authentication.api.login_screen.LoginScreenNavDependencies
import com.example.authentication.api.login_screen.LoginScreenViewModel
import com.example.common.navigation.NavDependenciesProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel
) {
    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(LoginScreenNavDependencies::class.java)

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    com.example.authentication.api.login_screen.LoginScreen(
        viewModel = viewModel
    )
}