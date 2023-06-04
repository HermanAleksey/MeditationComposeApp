package com.example.feature.main.api

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.NavigationBaseViewModel
import com.example.feature.main.internal.composable.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : NavigationBaseViewModel<MainScreenNavRoute>() {

    fun onMenuItemClick(menuItem: MenuItem) = viewModelScope.launch {
        navigationEventTransaction {
            when (menuItem) {
                MenuItem.PUZZLE -> {
                    _navigationEvent.emit(
                        MainScreenNavRoute.PuzzleScreen()
                    )
                }
                MenuItem.MUSIC -> {
                    _navigationEvent.emit(
                        MainScreenNavRoute.MusicScreen()
                    )
                }
            }
        }
    }
}