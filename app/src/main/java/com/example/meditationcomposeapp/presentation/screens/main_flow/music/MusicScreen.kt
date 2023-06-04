package com.example.meditationcomposeapp.presentation.screens.main_flow.music

import androidx.compose.runtime.Composable
import com.example.feature.music_player.ui.viewmodels.MusicScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MusicScreen(
    viewModel: MusicScreenViewModel,
) {
    com.example.feature.music_player.ui.MusicScreen(viewModel)
}