package com.example.feature.profile.api

import androidx.compose.runtime.Composable
import com.example.feature.profile.internal.InternalProfileScreen
import com.example.feature.profile.internal.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
) {
    InternalProfileScreen(viewModel)
}