package com.wongislandd.infinityindex.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Primary and secondary colors
val primaryColor = Color(0xFFB22222) // Dark Red (Iron Man-inspired)
val secondaryColor = Color(0xFFFFC107) // Marvel Gold (Iron Man's arc reactor)

// Background color (darkish gray for dark theme, white for light theme)
val backgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF121212) else Color(0xFFFFFFFF) // Dark gray for dark mode, white for light mode

// Text color (soft off-white/light gray for dark background, black for light theme)
val textColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF000000) // Light gray for dark theme, black for light theme

// Surface color (matches background color)
val surfaceColor
    @Composable
    get() = backgroundColor

val MarvelColors
    @Composable
    get() = if (isSystemInDarkTheme()) {
        darkColors(
            primary = primaryColor,
            onPrimary = Color.White, // White text on dark red
            secondary = secondaryColor, // Marvel Gold for secondary color
            background = backgroundColor, // Dark background
            surface = surfaceColor, // Surface color matches background
            onSurface = textColor, // Text color for surface (soft light gray text color)
            onBackground = textColor // Text color for background (soft light gray text color)
        )
    } else {
        lightColors(
            primary = primaryColor,
            onPrimary = Color.Black, // Black text on dark red
            secondary = secondaryColor, // Marvel Gold for secondary color
            background = backgroundColor, // White background in light mode
            surface = surfaceColor, // Surface color matches background
            onSurface = textColor, // Text color for surface (black text on white surface)
            onBackground = textColor // Text color for background (black text on white background)
        )
    }

@Composable
fun MarvelTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MarvelColors,
        content = content
    )
}