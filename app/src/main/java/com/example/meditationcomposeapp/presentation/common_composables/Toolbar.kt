package com.example.meditationcomposeapp.presentation.common_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meditationcomposeapp.R

@Composable
fun Toolbar(
    onUpdateHistoryClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.height_toolbar))
            .background(color = MaterialTheme.colors.background)
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_main_content)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_hamburger),
            contentDescription = "ic_hamburger image",
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_logo_white),
            contentDescription = "ic_logo_white image",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            imageVector = Icons.Rounded.Newspaper,
            contentDescription = "Updates news",
            modifier = Modifier
                .size(28.dp)
                .clickable { onUpdateHistoryClick() },
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
    }
}