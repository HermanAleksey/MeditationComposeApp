package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.screens.destinations.ShufflePuzzleScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

fun getMenuItemsList(navigator: DestinationsNavigator) = listOf(
    MenuItemModel(
        title = "Shuffle Puzzle",
        painterRes = R.drawable.ic_check_icon_main,
        backgroundColor = Color(169, 213, 113),
        foregroundColor = Color(106, 174, 114),
        onClick = { navigator.navigate(ShufflePuzzleScreenDestination()) },
    ),
    MenuItemModel(
        title = "Mood Booster",
        painterRes = R.drawable.ic_rocket,
        backgroundColor = Color(104, 175, 156),
        foregroundColor = Color(73, 138, 120)
    ),
    MenuItemModel(
        title = "Lego",
        painterRes = R.drawable.ic_lego,
        backgroundColor = Color(62, 132, 105),
        foregroundColor = Color(43, 91, 84)
    ),
    MenuItemModel(
        title = "Chill Relax",
        painterRes = R.drawable.ic_audacity,
        backgroundColor = Color(106, 174, 114),
        foregroundColor = Color(62, 132, 105)
    ),
    MenuItemModel(
        title = "Super Finger",
        painterRes = R.drawable.ic_touch_id,
        backgroundColor = Color(154, 154, 154),
        foregroundColor = Color(177, 177, 177)
    )
)