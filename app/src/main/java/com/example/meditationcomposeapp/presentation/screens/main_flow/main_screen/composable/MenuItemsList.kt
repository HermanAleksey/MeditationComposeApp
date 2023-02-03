package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import com.example.common.utils.UiText
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.screens.destinations.ShufflePuzzleScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

fun getMenuItemsList(navigator: DestinationsNavigator) = listOf(
    MenuItemModel(
        title = UiText.StringResource(R.string.shuffle_puzzle_menu_option),
        icon = Icons.Default.Extension,
        backgroundColor = Color(169, 213, 113),
        foregroundColor = Color(106, 174, 114),
        onClick = { navigator.navigate(ShufflePuzzleScreenDestination()) },
    ),
    MenuItemModel(
        title = UiText.StringResource(R.string.menu_item_coming_soon),
        icon = Icons.Default.Map,
        backgroundColor = Color(104, 175, 156),
        foregroundColor = Color(73, 138, 120)
    ),
    MenuItemModel(
        title = UiText.StringResource(R.string.menu_item_coming_soon),
        icon = Icons.Default.MusicNote,
        backgroundColor = Color(62, 132, 105),
        foregroundColor = Color(43, 91, 84)
    ),
    MenuItemModel(
        title = UiText.StringResource(R.string.menu_item_coming_soon),
        icon = Icons.Default.CameraAlt,
        backgroundColor = Color(106, 174, 114),
        foregroundColor = Color(62, 132, 105)
    ),
    MenuItemModel(
        title = UiText.StringResource(R.string.menu_item_coming_soon),
        icon = Icons.Default.AcUnit,
        backgroundColor = Color(154, 154, 154),
        foregroundColor = Color(177, 177, 177)
    )
)