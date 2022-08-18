package com.example.nbateams.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = orange_200,
    primaryVariant = orange_700,
    secondary = orange_200,
    secondaryVariant = orange_700,
    onPrimary = black,
    onSecondary = black,
    surface = black,
    onBackground = transparent_black
)

private val LightColorPalette = lightColors(
    primary = orange_500,
    primaryVariant = orange_700,
    secondary = orange_200,
    secondaryVariant = orange_700,
    onPrimary = white,
    onSecondary = white,
    surface = white,
    onBackground = transparent_orange_700
)

@Composable
fun NBATheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}