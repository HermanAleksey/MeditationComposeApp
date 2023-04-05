package com.example.internet_connection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.AppTheme


@Composable
fun NoInternetConnectionImage() {
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.no_internet_connection),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.meditating_girl),
            contentDescription = null
        )
    }
}

@Preview
@Composable
internal fun NoInternetConnectionImagePreview() {
    AppTheme {
        Box(Modifier.background(MaterialTheme.colors.background)) {
            NoInternetConnectionImage()
        }
    }
}