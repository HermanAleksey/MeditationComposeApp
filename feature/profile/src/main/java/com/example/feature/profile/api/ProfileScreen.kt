package com.example.feature.profile.api

import androidx.compose.runtime.Composable
import com.example.feature.profile.internal.InternalProfileScreen

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
) {
    InternalProfileScreen(viewModel)
}