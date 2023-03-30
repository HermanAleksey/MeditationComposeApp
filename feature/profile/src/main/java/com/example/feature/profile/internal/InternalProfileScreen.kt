package com.example.feature.profile.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.data_store.use_case.ClearAuthDataUseCase
import com.example.design_system.AppTheme
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
        Text(
            text = "profile screen",
            style = MaterialTheme.typography.h4
        )
        Button(
            onClick = {
                viewModel.onLogOutClicked()
            }
        ) {
            Text(
                text = "Log out",
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Preview
@Composable
private fun InternalProfileScreenPreview() {
    AppTheme {
        InternalProfileScreen(
            ProfileScreenViewModel(object : ClearAuthDataUseCase {
                override suspend fun invoke() {
//                    TODO("Not yet implemented")
                }
            })
        )
    }
}