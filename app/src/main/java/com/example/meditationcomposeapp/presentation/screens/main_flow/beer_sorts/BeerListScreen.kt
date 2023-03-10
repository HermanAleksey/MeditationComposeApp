package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_sorts

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.beer_sorts.api.BeerListNavDependencies
import com.example.beer_sorts.api.BeerListScreenViewModel
import com.example.common.navigation.NavDependenciesProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel,
) {
    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(BeerListNavDependencies::class.java)

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenEntered()
    }

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    com.example.beer_sorts.api.BeerListScreen(
        viewModel = viewModel,
    )
}
