package com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.utils.resources.UiText
import com.example.meditationcomposeapp.presentation.screens.destinations.BeerListScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.TestScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestinations(
    val direction: DirectionDestinationSpec,
    val description: UiText,
    val icon: ImageVector,
) {
    Main(
        direction = MainScreenDestination,
        description = UiText.StringResource(R.string.home_screen_desc),
        icon = Icons.Default.Home
    ),

    BeerList(
        direction = BeerListScreenDestination,
        description = UiText.StringResource(R.string.beers_screen_desc),
        icon = Icons.Default.Coffee
    ),

    TestScreen(
        direction = TestScreenDestination,
        description = UiText.StringResource(R.string.profile_screen_desc),
        icon = Icons.Default.Logout
    );
}