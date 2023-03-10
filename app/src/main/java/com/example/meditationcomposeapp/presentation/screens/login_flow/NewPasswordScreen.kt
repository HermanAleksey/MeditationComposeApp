package com.example.meditationcomposeapp.presentation.screens.login_flow

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.authentication.api.new_password_screen.NewPasswordScreenNavDependencies
import com.example.authentication.api.new_password_screen.NewPasswordScreenViewModel
import com.example.common.navigation.NavDependenciesProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun NewPasswordScreen(
    viewModel: NewPasswordScreenViewModel,
    login: String,
) {
    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(NewPasswordScreenNavDependencies::class.java)

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    com.example.authentication.api.new_password_screen.NewPasswordScreen(
        viewModel = viewModel,
        login = login,
    )
}
