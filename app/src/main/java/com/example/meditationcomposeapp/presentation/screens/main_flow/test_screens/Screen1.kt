package com.example.meditationcomposeapp.presentation.screens.main_flow.test_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.usecase.authentication.ClearAuthDataUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Destination
@Composable
fun screen3(
    viewModel: testScreenViewModel,
    navigator: DestinationsNavigator
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
class testScreenViewModel @Inject constructor(
    private val clearAuthDataUseCase: ClearAuthDataUseCase
) : ViewModel() {

    fun onLogOutClicked(navigator: DestinationsNavigator) {
        viewModelScope.launch {
            clearAuthDataUseCase()
            navigator.navigate(
                EnterScreenDestination()
            )
        }
    }
}