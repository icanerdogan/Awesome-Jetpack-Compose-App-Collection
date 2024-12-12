package com.ibrahimcanerdogan.notepadapp.ui.theme

import android.app.Activity
import android.content.res.Resources.Theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColorScheme(
    primary = ThemeColors.Night.primary,
    onPrimary = ThemeColors.Night.text,
    surface = ThemeColors.Night.surface,
    background = ThemeColors.Night.background
)

private val LightColorScheme = lightColorScheme(
    primary = ThemeColors.Day.primary,
    onPrimary = ThemeColors.Day.text,
    surface = ThemeColors.Day.surface,
    background = ThemeColors.Day.background
)

@Composable
fun NotepadAppLightTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val window = (view.context as Activity).window
    window?.statusBarColor = LightColorScheme.surface.toArgb()

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun NotepadAppDarkTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val window = (view.context as Activity).window
    window?.statusBarColor = DarkColorScheme.surface.toArgb()

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}