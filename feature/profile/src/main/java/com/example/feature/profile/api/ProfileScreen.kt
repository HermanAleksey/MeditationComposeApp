package com.example.feature.profile.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.data_store.use_case.ClearAuthDataUseCase
import com.example.design_system.AppTheme
import com.example.feature.profile.internal.InternalProfileScreen

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel
) {
    InternalProfileScreen(viewModel)
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            viewModel = ProfileScreenViewModel(
                object : ClearAuthDataUseCase {
                    override suspend fun invoke() {
//                   mock
                    }
                }
            )
        )
    }
}