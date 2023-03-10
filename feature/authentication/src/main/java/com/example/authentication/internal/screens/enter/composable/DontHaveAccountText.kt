package com.example.authentication.internal.screens.enter.composable

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.feature.authentication.R

@Composable
internal fun DontHaveAccountText(
    modifier: Modifier,
) {
    Text(
        text = buildAnnotatedString {
            val dontHaveAccountLabel = stringResource(id = R.string.dont_have_account)
            val signUpLabel = stringResource(id = R.string.sign_up)
            withStyle(
                style = ParagraphStyle(lineHeight = 20.sp),
            ) {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W200,
                    )
                ) {
                    append(dontHaveAccountLabel)
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                    )
                ) {
                    append(signUpLabel)
                }
            }
        },
        style = MaterialTheme.typography.subtitle1,
        modifier = modifier
    )
}