package com.justparokq.feature.internet_connection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.common_composables.DefaultAppBackground

@Composable
fun NoInternetConnectionScreen(onReturnButton: () -> Unit) {
    DefaultAppBackground {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(48.dp))
            NoInternetConnectionImage()
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.no_internet_connection),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.no_internet_connection_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 48.dp)
            )
            Spacer(modifier = Modifier.height(37.dp))
            Button(
                onClick = { onReturnButton() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = stringResource(R.string.return_label),
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 45.dp),
                )
            }
        }
    }
}

@Preview
@Composable
internal fun NoInternetConnectionScreenPreview() {
    AppTheme {
        Box(Modifier.background(MaterialTheme.colors.background)) {
            NoInternetConnectionScreen {}
        }
    }
}