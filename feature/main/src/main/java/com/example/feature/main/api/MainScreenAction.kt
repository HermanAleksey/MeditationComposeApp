package com.example.feature.main.api

import com.justparokq.core.common.mvi.MviAction
import com.example.feature.main.internal.composable.MenuItem

interface MainScreenAction : MviAction {

    object TitleLongClick: MainScreenAction

    data class MenuItemClick(
        val menuItem: MenuItem
    ): MainScreenAction
}