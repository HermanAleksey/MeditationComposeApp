package com.justparokq.feature.music_player.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.justparokq.feature.music_player.data.entities.DataSource
import com.justparokq.feature.music_player.data.entities.LocalRes
import com.justparokq.feature.music_player.data.entities.LocalURL
import com.justparokq.feature.music_player.data.entities.WebURL
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MediaResourceGlideImage(
    modifier: Modifier = Modifier,
    imageSource: DataSource
) {
    GlideImage(
        imageModel = {
            when (imageSource) {
                is LocalRes -> imageSource.resId
                is WebURL -> imageSource.url
                is LocalURL -> throw Throwable("error")
            }
        },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        modifier = modifier,
    )
}