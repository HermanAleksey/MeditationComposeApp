package com.example.meditationcomposeapp.presentation.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(
    viewModel: RegistrationScreenViewModel,
    navController: NavController
) {
    Column {
        Text(text = "REGISTRATION SCREEN")
    }
}