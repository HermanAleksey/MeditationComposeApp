package com.justparokq.feature.authentication.internal.screens.registration.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.authentication.R

@Composable
internal fun AlreadyHaveAccountText(
    onClick: () -> Unit,
) {
    Text(
        text = buildAnnotatedString {
            val alreadyHaveAccountLabel = stringResource(id = R.string.already_have_account)
            val signInLabel = stringResource(id = R.string.sign_in)
            val style = MaterialTheme.typography.h6

            withStyle(
                style = style.copy(
                    fontWeight = FontWeight.W200
                ).toSpanStyle()
            ) {
                append(alreadyHaveAccountLabel)
            }
            append(" ")
            withStyle(
                style = style.copy(
                    fontWeight = FontWeight.W700,
                ).toSpanStyle()
            ) {
                append(signInLabel)
            }
        },
        textAlign = TextAlign.Center,
        modifier = Modifier.clickable {
            onClick()
        }
    )
}

@Preview
@Composable
private fun AlreadyHaveAccountTextPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            AlreadyHaveAccountText(
                onClick = {}
            )
        }
    }
}