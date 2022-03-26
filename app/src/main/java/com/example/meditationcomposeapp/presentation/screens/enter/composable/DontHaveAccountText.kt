package com.example.meditationcomposeapp.presentation.screens.enter.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import com.example.meditationcomposeapp.R

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DontHaveAccountText(
    modifier: Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(lineHeight = 20.sp),
            ) {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W200
                    )
                ) {
                    append(stringResource(id = R.string.dont_have_account))
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                ) {
                    append(stringResource(id = R.string.sign_up))
                }
            }
        },
        fontSize = TextUnit(20F, TextUnitType.Sp),
        color = Color.White,
        modifier = modifier
    )
}