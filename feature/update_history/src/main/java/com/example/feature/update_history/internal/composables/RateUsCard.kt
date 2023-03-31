package com.example.feature.update_history.internal.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.design_system.clickableWithoutRipple
import com.example.feature.update_history.R

@Composable
internal fun RateUsCard(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onRateUsClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 40.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = stringResource(R.string.do_you_like_app),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { onCancelClick() }) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = MaterialTheme.typography.button
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { onRateUsClick() }) {
                    Text(
                        text = stringResource(R.string.rate_us),
                        style = MaterialTheme.typography.button
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.share_opinion_label),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.clickableWithoutRipple {
                    //todo open mail to address 'justparocq@gmail.com'
                }
            )
        }
    }
}

@Preview
@Composable
private fun RateUsCardPreview() {
    AppTheme {
        RateUsCard(
            onCancelClick = {},
            onRateUsClick = {}
        )
    }
}