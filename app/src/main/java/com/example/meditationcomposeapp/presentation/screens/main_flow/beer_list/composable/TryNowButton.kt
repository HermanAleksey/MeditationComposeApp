package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R

@Composable
fun TryNowButton() {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(45.dp)
            .width(140.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.try_now),
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(6.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_play_icon),
                contentDescription = "",
                modifier = Modifier.size(13.dp)
            )
        }
    }
}

@Preview
@Composable
fun previewTryNowButton() {
    TryNowButton()
}