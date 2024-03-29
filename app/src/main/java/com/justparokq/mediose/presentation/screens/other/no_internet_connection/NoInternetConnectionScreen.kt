package com.justparokq.mediose.presentation.screens.other.no_internet_connection

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.justparokq.core.common.navigation.NavDependenciesProvider
import com.justparokq.feature.internet_connection.NoInternetConnectionNavDependencies
import com.justparokq.feature.internet_connection.NoInternetConnectionViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun NoInternetConnectionScreen(
    viewModel: NoInternetConnectionViewModel,
) {
    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(NoInternetConnectionNavDependencies::class.java)

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    com.justparokq.feature.internet_connection.NoInternetConnectionScreen(
        onReturnButton = {
            viewModel.onBackClick()
        }
    )
}