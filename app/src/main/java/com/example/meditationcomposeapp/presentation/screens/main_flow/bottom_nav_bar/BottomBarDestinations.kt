package com.example.meditationcomposeapp.presentation.screens.main_flow.bottom_nav_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.meditationcomposeapp.presentation.screens.destinations.BeerListScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.TestScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestinations(
    val direction: DirectionDestinationSpec,
    val title: String,
    val icon: ImageVector,
) {
    Main(
        direction = MainScreenDestination,
        title = "Main",
        icon = Icons.Default.Warning
    ),

    BeerList(
        direction = BeerListScreenDestination,
        title = "Beers",
        icon = Icons.Default.Star
    ),

    TestScreen(
        direction = TestScreenDestination,
        title = "Log out",
        icon = Icons.Default.Email
    );
}