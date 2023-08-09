package com.justparokq.mediose.presentation.screens.login_flow

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.authentication.api.enter_code_screen.EnterCodeScreenNavDependencies
import com.example.authentication.api.enter_code_screen.EnterCodeScreenViewModel
import com.justparokq.core.common.navigation.NavDependenciesProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EnterCodeScreen(
    viewModel: EnterCodeScreenViewModel,
    login: String
) {
    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(EnterCodeScreenNavDependencies::class.java)

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    com.example.authentication.api.enter_code_screen.EnterCodeScreen(
        viewModel = viewModel,
        login = login
    )
}
