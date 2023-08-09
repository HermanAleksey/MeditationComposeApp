package com.justparokq.feature.music_player.ui.composables.music_playlist.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.clickableWithoutRipple
import com.justparokq.feature.music_player.R

//todo implement animation switch
@Composable
fun FavouriteCheckbox(
    size: Dp,
    isChecked: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(
                id = if (isChecked)
                    R.drawable.ic_favourite_checked
                else R.drawable.ic_favourite_unchecked
            ),
            contentDescription = stringResource(id = R.string.favourite_button_content_desc),
            modifier = Modifier
                .clickableWithoutRipple {
                    onClick()
                }
                .size(size * 2 / 3),
            tint = MaterialTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
fun FavouriteCheckboxChecked() {
    AppTheme {
        FavouriteCheckbox(
            isChecked = true,
            size = 36.dp,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun FavouriteCheckboxUnchecked() {
    AppTheme {
        FavouriteCheckbox(
            isChecked = false,
            size = 36.dp,
            onClick = {}
        )
    }
}