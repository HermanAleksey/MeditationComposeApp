package com.example.meditationcomposeapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R

val Alegreya = FontFamily(
    Font(R.font.alegreya_thin_100, FontWeight.W100),
    Font(R.font.alegreya_light_300, FontWeight.W300),
    Font(R.font.alegreya_regular_400, FontWeight.W400),
    Font(R.font.alegreya_medium_500, FontWeight.W500),
    Font(R.font.alegreya_bold_700, FontWeight.W700),
    Font(R.font.alegreya_extrabold_800, FontWeight.W800),
    Font(R.font.alegreya_black_900, FontWeight.W900)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)