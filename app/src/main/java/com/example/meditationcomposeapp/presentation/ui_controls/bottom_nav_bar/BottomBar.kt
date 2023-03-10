package com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar.state.NavigationBottomBar

sealed class BottomBarState {

    class NavigationBottomBarState(val navController: NavController) : BottomBarState()
}

@Composable
fun BottomBar(state: BottomBarState) {
    when (state) {
        is BottomBarState.NavigationBottomBarState -> NavigationBottomBar(state.navController)
    }
}
