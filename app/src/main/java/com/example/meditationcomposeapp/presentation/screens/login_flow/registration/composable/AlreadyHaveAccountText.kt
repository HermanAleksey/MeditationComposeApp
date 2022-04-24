package com.example.meditationcomposeapp.presentation.screens.login_flow.registration.composable

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
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AlreadyHaveAccountText(
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
                    append(stringResource(id = R.string.already_have_account))
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                ) {
                    append(stringResource(id = R.string.sign_in))
                }
            }
        },
        fontSize = TextUnit(20F, TextUnitType.Sp),
        color = Color.White,
        modifier = modifier
    )
}