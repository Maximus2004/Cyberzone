package com.example.cyberzone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cyberzone.R

val Monserrat = FontFamily(
    Font(R.font.montserrat_medium, FontWeight.W500),
    Font(R.font.montserrat_semi_bold, FontWeight.W600)
)

val Syncopate = FontFamily(
    Font(R.font.syncopate_bold_cyr, FontWeight.W700),
    Font(R.font.syncopate_regular, FontWeight.W400)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        color = WhiteAlpha
    ),
    bodySmall = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = White
    ),
    titleLarge = TextStyle(
        fontFamily = Syncopate,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 32.sp,
        color = White
    ),
    displayMedium = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        color = White
    ),
    titleSmall = TextStyle(
        fontFamily = Syncopate,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 9.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Syncopate,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        color = White
    ),
    labelSmall = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        color = White
    ),
    displayLarge = TextStyle(
        fontFamily = Syncopate,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 12.sp,
        color = White
    )
)