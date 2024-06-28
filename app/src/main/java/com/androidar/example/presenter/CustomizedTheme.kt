package com.androidar.example.presenter

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColorScheme(
    primary = Color(0xFFE8E9E9),
    onPrimary = Color.Black,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    surface = LightThemeColors.SURFACE,
    tertiary = LightThemeColors.EMERGENCY_CALL,

    )

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF330044),
    onPrimary = Color.Black,
    secondary = Color.White.copy(0.12f),
    onSecondary = Color.White,
    background = Color.Black,
    surface = DarkThemeColors.SURFACE,
    tertiary = DarkThemeColors.EMERGENCY_CALL
)

@Composable
fun CustomizedThemes(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}