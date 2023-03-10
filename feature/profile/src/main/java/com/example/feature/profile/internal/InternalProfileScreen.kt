package com.example.feature.profile.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.feature.profile.api.ProfileScreenViewModel

@Composable
internal fun InternalProfileScreen(
    viewModel: ProfileScreenViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "profile screen")
        Button(
            onClick = {
                viewModel.onLogOutClicked()
            }
        ) {
            Text(text = "Log out")
        }
    }
}