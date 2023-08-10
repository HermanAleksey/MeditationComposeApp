package com.justparokq.feature.main.api

import com.justparokq.core.common.mvi.MviAction
import com.justparokq.feature.main.internal.composable.MenuItem

interface MainScreenAction : MviAction {

    object TitleLongClick: MainScreenAction

    data class MenuItemClick(
        val menuItem: MenuItem
    ): MainScreenAction
}