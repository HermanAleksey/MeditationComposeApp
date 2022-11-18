package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TestScreen(
    viewModel: ProfileScreenViewModel,
    navigator: DestinationsNavigator,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "profile screen")
        Button(onClick = {
            viewModel.onLogOutClicked {
                navigator.navigate(
                    EnterScreenDestination()
                ) {
                    popUpTo(SplashScreenDestination().route)
                }
            }
        }) {
            Text(text = "Log out")
        }
    }
}