package com.example.meditationcomposeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

//source for material themes
//https://material.io/design/color/the-color-system.html#color-theme-creation
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    //main brand color (app bar uses it)
    primary = Purple500,
    primaryVariant = Purple700,
    //used to provide accents
    secondary = ColorSecondary,
    //screen background uses it
    surface = ColorPlatinum,
    //app background uses it
    background = Green700,


    //Material also defines "on" colors—colors to use for content on top of one of the named colors
    // e.g. text in a ‘surface' colored container should be colored ‘on surface'.

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MeditationComposeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}