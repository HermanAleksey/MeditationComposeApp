package com.example.authentication.internal.screens.registration.composable

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.feature.authentication.R

@Composable
internal fun AlreadyHaveAccountText(
    modifier: Modifier
) {

    Text(
        text = buildAnnotatedString {
            val alreadyHaveAccountLabel = stringResource(id = R.string.already_have_account)
            val signInLabel = stringResource(id = R.string.sign_in)
            withStyle(
                style = ParagraphStyle(lineHeight = 20.sp),
            ) {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W200
                    )
                ) {
                    append(alreadyHaveAccountLabel)
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W700,
                    )
                ) {
                    append(signInLabel)
                }
            }
        },
        style = MaterialTheme.typography.body1.copy(fontSize = 20.sp),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}