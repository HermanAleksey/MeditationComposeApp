package com.example.beer_sorts.internal.presentation.beer_list.composables

import android.app.SearchManager
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.feature.beer_sorts.R


@Composable
internal fun TryNowButton(beerName: String, tagline: String) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(45.dp)
            .width(140.dp)
            .clickable {
                Intent(Intent.ACTION_WEB_SEARCH)
                    .apply {
                        putExtra(SearchManager.QUERY, "$beerName, $tagline")
                    }
                    .let {
                        startActivity(context, it, null)
                    }
            },
        backgroundColor = MaterialTheme.colors.secondary,
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
                contentDescription = null,
                modifier = Modifier.size(13.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewTryNowButton() {
    TryNowButton("Beer", "tagline")
}