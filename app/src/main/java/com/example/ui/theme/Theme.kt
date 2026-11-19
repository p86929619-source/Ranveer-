package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RoyalBlue80,
    onPrimary = RoyalBlue10,
    primaryContainer = RoyalBlue30,
    onPrimaryContainer = Color.White,
    secondary = AmberGold80,
    onSecondary = OnAmberGoldContainer,
    secondaryContainer = OnAmberGoldContainer,
    onSecondaryContainer = AmberGoldContainer,
    tertiary = EmeraldGreen80,
    onTertiary = OnEmeraldGreenContainer,
    tertiaryContainer = OnEmeraldGreenContainer,
    onTertiaryContainer = EmeraldGreenContainer,
    background = SurfaceDark,
    onBackground = Color(0xFFF1F5F9),
    surface = CardDark,
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = Color(0xFF1E293B),
    onSurfaceVariant = Color(0xFF94A3B8),
    outline = OutlineDark
)

private val LightColorScheme = lightColorScheme(
    primary = RoyalBlue40,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDBEAFE),
    onPrimaryContainer = RoyalBlue30,
    secondary = PaidOrange,
    onSecondary = Color.White,
    secondaryContainer = AmberGoldContainer,
    onSecondaryContainer = OnAmberGoldContainer,
    tertiary = EmeraldGreen40,
    onTertiary = Color.White,
    tertiaryContainer = EmeraldGreenContainer,
    onTertiaryContainer = OnEmeraldGreenContainer,
    background = SurfaceLight,
    onBackground = Color(0xFF0F172A),
    surface = CardLight,
    onSurface = Color(0xFF111827),
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = Color(0xFF4B5563),
    outline = OutlineLight
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
