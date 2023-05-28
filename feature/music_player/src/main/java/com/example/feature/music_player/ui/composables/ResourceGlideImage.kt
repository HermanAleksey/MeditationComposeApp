package com.example.feature.music_player.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.feature.music_player.data.entities.MediaDataSource
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MediaResourceGlideImage(
    modifier: Modifier = Modifier,
    imageSource: MediaDataSource
) {
    GlideImage(
        imageModel = {
            when (imageSource) {
                is MediaDataSource.LocalSource -> imageSource.resId
                is MediaDataSource.WebSource -> imageSource.url
            }
        },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        modifier = modifier,
    )
}