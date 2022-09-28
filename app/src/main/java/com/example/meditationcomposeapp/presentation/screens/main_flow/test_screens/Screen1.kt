package com.example.meditationcomposeapp.presentation.screens.main_flow.test_screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.usecase.authentication.ClearAuthDataUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.SplashScreenDestination
import com.example.shuffle_puzzle.model.Piece
import com.example.shuffle_puzzle.model.Puzzle
import com.example.shuffle_puzzle.presentation.PuzzleBoard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Destination
@Composable
fun TestScreen(
    viewModel: TestScreenViewModel,
    navigator: DestinationsNavigator,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "screen  3")
        Button(onClick = {
            viewModel.onLogOutClicked(navigator)
        }) {
            Text(text = "Log out")
        }
    }
}


@HiltViewModel
class TestScreenViewModel @Inject constructor(
    private val clearAuthDataUseCase: ClearAuthDataUseCase,
) : ViewModel() {

    fun onLogOutClicked(navigator: DestinationsNavigator) {
        viewModelScope.launch {
            clearAuthDataUseCase()
            navigator.navigate(
                EnterScreenDestination()
            ) {
                popUpTo(SplashScreenDestination().route)
            }
        }
    }
}