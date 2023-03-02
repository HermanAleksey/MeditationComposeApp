package com.example.feature.main.api

import com.example.common.view_model.NavigationBaseViewModel
import com.example.feature.main.internal.composable.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : NavigationBaseViewModel<MainScreenNavRoute>() {

    fun onMenuItemClick(menuItem: MenuItem) {
        when (menuItem) {
            MenuItem.PUZZLE -> {
                _navigationEvent.update {
                    MainScreenNavRoute.PuzzleScreen()
                }
            }
        }
    }
}