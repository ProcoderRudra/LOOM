package com.loom.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LoomColors.PrimaryBlue,
    secondary = LoomColors.PrimaryBlueBright,
    tertiary = LoomColors.TelegramBlue,
    background = LoomColors.BackgroundDark,
    surface = LoomColors.SurfaceDark,
    onBackground = LoomColors.TextWhite,
    onSurface = LoomColors.TextWhite,
    onPrimary = LoomColors.BackgroundDark,
    error = LoomColors.ErrorRed,
    outline = LoomColors.TextMuted
)

@Composable
fun LoomTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = LoomTypography,
        content = content
    )
}
