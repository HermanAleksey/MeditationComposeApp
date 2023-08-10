package com.justparokq.feature.update_history.internal.composables

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.clickableWithoutRipple
import com.justparokq.feature.update_history.R


@Composable
internal fun RateUsCard(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onRateUsClick: () -> Unit,
) {
    val context = LocalContext.current
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
                    context.sendMail(context.getString(R.string.mail_subject_rate_us))
                },
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

private fun Context.sendMail(subject: String) {
    try {
        val selectorIntent = Intent(Intent.ACTION_SENDTO)
        selectorIntent.data = Uri.parse("mailto:$FEEDBACK_EMAIL_ADDRESS")

        Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(FEEDBACK_EMAIL_ADDRESS))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            selector = selectorIntent
        }.let {
            startActivity(it)
        }
    } catch (e: ActivityNotFoundException) {
        Log.e("RateUsCard", "sendMail: don't have Activity to send email")
    } catch (t: Throwable) {
        Log.e("RateUsCard", "sendMail: e:$t")
    }
}

const val FEEDBACK_EMAIL_ADDRESS: String = "justparocq@gmail.com"

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