package com.example.feature.main.api

import com.example.common.mvi.MviAction
import com.example.feature.main.internal.composable.MenuItem

interface MainScreenAction : MviAction {

    object TitleLongClick: MainScreenAction

    data class MenuItemClick(
        val menuItem: MenuItem
    ): MainScreenAction
}