package com.wongislandd.infinityindex.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun InfinityIndexLoadingScreen() {
    PulsingAnimation()
}

@Composable
fun PulsingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val pulseFraction by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val primaryColor = MaterialTheme.colors.primary
    val secondaryColor = MaterialTheme.colors.secondary

    // Interpolate colors manually
    val pulseColor = interpolateColor(primaryColor, secondaryColor, pulseFraction * 0.3f) // Limit to 30% gold

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pulseColor)
    ) {
        Text(
            text = "Loading...",
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/**
 * Interpolates between two colors based on the given fraction.
 */
fun interpolateColor(startColor: Color, endColor: Color, fraction: Float): Color {
    val startRed = startColor.red
    val startGreen = startColor.green
    val startBlue = startColor.blue
    val startAlpha = startColor.alpha

    val endRed = endColor.red
    val endGreen = endColor.green
    val endBlue = endColor.blue
    val endAlpha = endColor.alpha

    val red = startRed + (endRed - startRed) * fraction
    val green = startGreen + (endGreen - startGreen) * fraction
    val blue = startBlue + (endBlue - startBlue) * fraction
    val alpha = startAlpha + (endAlpha - startAlpha) * fraction

    return Color(red, green, blue, alpha)
}