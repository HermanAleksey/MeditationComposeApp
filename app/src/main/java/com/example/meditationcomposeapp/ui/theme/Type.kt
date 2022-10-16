package com.example.meditationcomposeapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_thin_100, FontWeight.W100, FontStyle.Normal),
    Font(R.font.montserrat_thin_italic_100, FontWeight.W100, FontStyle.Italic),

    Font(R.font.montserrat_light_300, FontWeight.W300, FontStyle.Normal),
    Font(R.font.montserrat_light_italic_300, FontWeight.W300, FontStyle.Italic),

    Font(R.font.montserrat_400, FontWeight.W400, FontStyle.Normal),
    Font(R.font.montserrat_italic_400, FontWeight.W400, FontStyle.Italic),

    Font(R.font.montserrat_medium_500, FontWeight.W500, FontStyle.Normal),
    Font(R.font.montserrat_medium_italic_500, FontWeight.W500, FontStyle.Italic),

    Font(R.font.montserrat_semibold_600, FontWeight.W600, FontStyle.Normal),
    Font(R.font.montserrat_semibold_italic_600, FontWeight.W600, FontStyle.Italic),

    Font(R.font.montserrat_bold_700, FontWeight.W700, FontStyle.Normal),
    Font(R.font.montserrat_bold_italic_700, FontWeight.W700, FontStyle.Italic),

    Font(R.font.montserrat_extra_bold_800, FontWeight.W800, FontStyle.Normal),
    Font(R.font.montserrat_extra_bold_italic_800, FontWeight.W800, FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    //app name
    h1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W700,
        fontSize = 34.sp,
        color = Color.White
    ),
    // screen titles
    h2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
        color = Color.White
    ),
//    h3 = TextStyle(
//
//    ),
//    h4 = TextStyle(
//
//    ),
//    h5 = TextStyle(
//
//    ),
//    h6 = TextStyle(
//
//    ),
    //login flow helper captures
    subtitle1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        color = Color.White,
    ),
    subtitle2 = TextStyle(
    ),
    //description text on screens.
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W400,
        fontSize = 22.sp,
        color = Color.White
    ),
    //standard input form
    body2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        color = Color.White
    ),
    button = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W400,
        fontSize = 25.sp,
        color = Color.White
    ),
    caption = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp,
        color = Color.White.copy(alpha = 0.7f)
    ),
//    overline = TextStyle(
//
//    ),
)