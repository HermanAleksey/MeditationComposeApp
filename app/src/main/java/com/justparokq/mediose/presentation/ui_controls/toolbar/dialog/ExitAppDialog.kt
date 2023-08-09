package com.justparokq.mediose.presentation.ui_controls.toolbar.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.AppTheme
import com.justparokq.mediose.R

@Composable
fun ExitAppDialog(
    onSubmitClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
        Text(
            text = stringResource(R.string.log_out_title),
            style = MaterialTheme.typography.h4.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.want_to_log_out_dialog_description),
            style = MaterialTheme.typography.h5.copy(
                color = MaterialTheme.colors.onSurface
            ),
            lineHeight = 24.sp,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { onSubmitClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.error
                )
            ) {
                Text(
                    text = stringResource(R.string.submit),
                    style = MaterialTheme.typography.button
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            Button(
                onClick = { onDismissRequest() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.button
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun ExitAppDialogPreview() {
    AppTheme {
        Card(modifier = Modifier, backgroundColor = MaterialTheme.colors.surface) {
            ExitAppDialog(onSubmitClick = {}, onDismissRequest = { })
        }
    }
}