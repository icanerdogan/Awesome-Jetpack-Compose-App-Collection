package com.ibrahimcanerdogan.jetmovielibraryapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.jetmovielibraryapp.R

val fontFamily = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_medium, FontWeight.Medium),
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = 45.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    bodyLarge = TextStyle(
        fontSize = 30.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    ),
    bodyMedium = TextStyle(
        fontSize = 25.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    ),
    bodySmall = TextStyle(
        fontSize = 15.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        color = Color.White,
    )
)