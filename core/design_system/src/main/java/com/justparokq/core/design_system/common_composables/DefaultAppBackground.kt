package com.justparokq.core.design_system.common_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.justparokq.core..design_system.R
import com.justparokq.core.design_system.AppTheme

@Composable
fun DefaultAppBackground(
    isLoading: Boolean = false,
    testTag: String = "",
    content: @Composable () -> Unit,
) {
    ColorBackground(
        content = {
            DecorationLeavesTop()
            DecorationLeavesBottom()
            content()
        },
        color = MaterialTheme.colors.background,
        isLoading = isLoading,
        testTag = testTag
    )
}

@Composable
private fun DecorationLeavesBottom() {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_bottom_leaves),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(1.2f)
                .wrapContentHeight()
        )
    }
}

@Composable
private fun DecorationLeavesTop() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.single_plant_top),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .wrapContentHeight()
        )
    }
}

@Preview
@Composable
private fun LoginMainButtonPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            DefaultAppBackground(
                isLoading = false
            ) {}
        }
    }
}