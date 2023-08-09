package com.justparokq.feature.beer_sorts.internal.common

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.beer_sorts.R


@Composable
internal fun TryNowButton(
    beerName: String,
    tagline: String,
) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val toastLabel = stringResource(R.string.text_is_copied)

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(45.dp)
            .width(140.dp)
            .clickable {
                val beerTitle = "$beerName, $tagline"

                try {
                    Intent(Intent.ACTION_WEB_SEARCH)
                        .apply {
                            putExtra(SearchManager.QUERY, beerTitle)
                        }
                        .let {
                            startActivity(context, it, null)
                        }
                } catch (e: ActivityNotFoundException) {
                    clipboardManager.setText(AnnotatedString((beerTitle)))
                    Toast
                        .makeText(context, toastLabel, Toast.LENGTH_SHORT)
                        .show()
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
                color = Color.White,
                textAlign = TextAlign.End
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
    AppTheme {
        TryNowButton("Beer", "tagline")
    }
}