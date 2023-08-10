package com.justparokq.core.design_system

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

//source for material themes
//https://material.io/design/color/the-color-system.html#color-theme-creation
private val LightColorPalette = lightColors(
    //main brand color (app bar uses it)
//    primary = Purple500,
    //used to provide accents
    secondary = Green300,
    //screen background uses it
    surface = ColorPalette,
    onSurface = Green700,
    //app background uses it
    background = Green700,
    onBackground = ColorPalette,
    error = AntiqueRuby,
    onError = ColorPalette
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = CrimsonTextTypography,
        shapes = Shapes,
        content = content
    )
}