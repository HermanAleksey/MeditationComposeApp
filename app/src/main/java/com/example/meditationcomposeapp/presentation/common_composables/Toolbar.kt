package com.example.meditationcomposeapp.presentation.common_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meditationcomposeapp.R

@Composable
fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = com.example.meditationcomposeapp.ui.theme.ColorBackground),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(25.dp))
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
            painter = painterResource(id = R.drawable.ic_hamburger),
            contentDescription = "profile picture image",
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(25.dp))
    }
}