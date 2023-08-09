package com.justparokq.feature.main.internal.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.ui.graphics.Color
import com.justparokq.core.common.utils.UiText
import com.justparokq.feature.main.R

enum class MenuItem {
    PUZZLE, MUSIC,
}

internal fun getMenuItemsList(onMenuItemClick: (MenuItem) -> Unit) = listOf(
    MenuItemModel(
        title = UiText.StringResource(R.string.shuffle_puzzle_menu_option),
        icon = Icons.Default.Extension,
        backgroundColor = Color(169, 213, 113),
        foregroundColor = Color(106, 174, 114),
        onClick = { onMenuItemClick(MenuItem.PUZZLE) },
    ),
    MenuItemModel(
        title = UiText.StringResource(R.string.music_menu_option),
        icon = Icons.Default.MusicNote,
        backgroundColor = Color(104, 175, 156),
        foregroundColor = Color(73, 138, 120),
        onClick = { onMenuItemClick(MenuItem.MUSIC) },
    ),
    MenuItemModel(
        title = UiText.StringResource(R.string.menu_item_coming_soon),
        icon = Icons.Default.Map,
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