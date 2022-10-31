package com.example.app.ui.theme

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter.Blur
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = DarkAction,
    primaryVariant = Purple700,
    secondary = DarkNeutralOne,
    surface = Color.Black,
    onPrimary = LightNeutralTwo,
    onSecondary = Color.White,
    onSurface = Color.White
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = DarkAction,
    primaryVariant = LightNeutralNine,
    secondary = LightNeutralTwo,
    surface = Color.White,
    onPrimary = LightNeutralTwo,
    onSecondary = LightNeutralNine,
    onSurface = LightNeutralSeven

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
fun StripeTerminalAppsOnDevicesTheme(
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
        shapes = Shapes) {
        content()
    }
}