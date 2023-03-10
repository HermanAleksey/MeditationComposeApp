package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.common.navigation.NavDependenciesProvider
import com.example.feature.profile.api.ProfileScreenNavDependencies
import com.example.feature.profile.api.ProfileScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
) {
    val navDependencies = ((LocalContext.current as? Activity) as? NavDependenciesProvider)
        ?.provideDependencies(ProfileScreenNavDependencies::class.java)

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.let { navRoute ->
                navDependencies?.let {
                    navRoute.tryNavigate(it)
                }
            }
        }
    }

    com.example.feature.profile.api.ProfileScreen(viewModel = viewModel)
}
