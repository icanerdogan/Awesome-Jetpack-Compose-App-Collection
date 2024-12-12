package com.ibrahimcanerdogan.notepadapp.ui.theme

import androidx.compose.ui.graphics.Color

sealed class ThemeColors(
    val primary: Color,
    val background: Color,
    val surface: Color,
    val text: Color
) {
    object Night: ThemeColors(
        background = Color(0xFF394867),
        surface = Color(0xFF212A3E),
        primary = Color(0xFF9BA4B5),
        text = Color(0xFFF1F6F9)
    )
    object Day: ThemeColors(
        background = Color(0xFFFFF7F1),
        surface = Color(0xFFFFE4C9),
        primary = Color(0xFFBED1CF),
        text = Color(0xFF000000)
    )
}