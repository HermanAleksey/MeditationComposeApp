package com.justparokq.mediose.presentation.screens.main_flow.beer_sorts

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.justparokq.feature.beer_sorts.api.beer_list.BeerListNavDependencies
import com.justparokq.feature.beer_sorts.api.beer_list.BeerListScreenViewModel
import com.justparokq.core.common.navigation.NavDependenciesProvider
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

    com.justparokq.feature.beer_sorts.api.beer_list.BeerListScreen(
        viewModel = viewModel,
    )
}
