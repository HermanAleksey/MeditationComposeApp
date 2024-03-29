package com.justparokq.feature.music_player.ui.composables.music_player.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.music_player.R
import com.justparokq.feature.music_player.data.entities.DataSource
import com.justparokq.feature.music_player.data.entities.LocalRes
import com.justparokq.feature.music_player.data.entities.LocalURL
import com.justparokq.feature.music_player.data.entities.WebURL
import com.justparokq.feature.music_player.ui.theme.roundedShape
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

private const val STOP_ANIMATION_DURATION = 1000
private const val CIRCLE_SPIN_ANIMATION_DURATION = 3000

@Composable
internal fun SpinDiskAnimation(
    modifier: Modifier = Modifier,
    isSpinning: Boolean = true,
    imageSource: DataSource,
) {
    var currentRotation by remember {
        mutableStateOf(0f)
    }

    val rotation = remember {
        Animatable(currentRotation)
    }

    LaunchedEffect(isSpinning) {
        if (isSpinning) {
            rotation.animateTo(
                targetValue = currentRotation + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = CIRCLE_SPIN_ANIMATION_DURATION,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        } else {
            if (currentRotation > 0f) {
                rotation.animateTo(
                    targetValue = currentRotation + 50,
                    animationSpec = tween(
                        durationMillis = STOP_ANIMATION_DURATION,
                        easing = LinearOutSlowInEasing
                    )
                ) {
                    currentRotation = value
                }
            }
        }
    }

    Box(
        modifier = modifier
            .aspectRatio(1.0f)
            .clip(roundedShape)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation.value),
            painter = painterResource(id = R.drawable.vinyl_background),
            contentDescription = null
        )

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
            modifier = Modifier
                .fillMaxSize(0.5f)
                .rotate(rotation.value)
                .aspectRatio(1.0f)
                .align(Alignment.Center)
                .clip(roundedShape),
        )
    }
}

@Preview
@Composable
fun SpinDiskAnimationPreview() {
    AppTheme {
        SpinDiskAnimation(
            modifier = Modifier,
            isSpinning = false,
            imageSource = LocalRes(R.drawable.vinyl_background)
        )
    }
}