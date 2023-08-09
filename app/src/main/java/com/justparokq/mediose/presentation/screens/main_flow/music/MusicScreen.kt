package com.justparokq.mediose.presentation.screens.main_flow.music

import androidx.compose.runtime.Composable
import com.justparokq.feature.music_player.ui.viewmodels.MusicScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MusicScreen(
    viewModel: MusicScreenViewModel,
) {
    com.justparokq.feature.music_player.ui.MusicScreen(viewModel)
}