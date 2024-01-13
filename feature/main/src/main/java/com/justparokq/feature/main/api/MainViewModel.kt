package com.justparokq.feature.main.api

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.core.common.view_model.NavigationBaseViewModel
import com.justparokq.feature.main.BuildConfig
import com.justparokq.feature.main.internal.composable.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : NavigationBaseViewModel<MainScreenNavRoute>(), MviViewModel<MainScreenState, MainScreenAction> {

    override val uiState: StateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState()).asStateFlow()

    override fun processAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.TitleLongClick -> {
                onTitleLongClick()
            }
            is MainScreenAction.MenuItemClick -> {
                onMenuItemClick(action.menuItem)
            }
        }
    }

    private fun onTitleLongClick() {
        if (BuildConfig.DEBUG) {
            Log.e("TAGG", "onTitleLongClick: ")
            viewModelScope.launch {
                navigationEventTransaction {
                    _navigationEvent.emit(
                        MainScreenNavRoute.FeatureToggleScreen()
                    )
                }
            }
        }
    }

    private fun onMenuItemClick(menuItem: MenuItem) = viewModelScope.launch {
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

                MenuItem.CHARTS_DEMO -> {
                    _navigationEvent.emit(
                        MainScreenNavRoute.ChartsDemoScreen()
                    )
                }

                MenuItem.CHAT -> {
                    _navigationEvent.emit(
                        MainScreenNavRoute.ChatScreen()
                    )
                }
            }
        }
    }
}