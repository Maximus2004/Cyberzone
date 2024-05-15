package com.example.cyberzone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = primary,
    onBackground = White,
    background = Black,
    secondaryContainer = secondaryContainer,
    surfaceVariant = surfaceVariant,
    scrim = scrim,
    tertiary = tertiary
)

@Composable
fun CyberzoneTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = Black)
    }

    val colorScheme = LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes(
            small = RoundedCornerShape(0.dp),
            medium = RoundedCornerShape(0.dp),
            large = RoundedCornerShape(0.dp)
        )
    )
}