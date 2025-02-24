package com.example.projectmvvmimpl.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF89CFF0),    // Baby Blue
    secondary = Color(0xFFFFDAB9),  // Peach
    tertiary = Color(0xFFB0E57C),   // Soft Green
    surface = Color(0xFFFDFCFB),    // Very Light Cream
    onSurface = Color(0xFF4A4A4A),  // Dark Gray (for readability)
    surfaceVariant = Color(0xFFEDE7F6) // Soft Lavender
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5),    // Deep Blue
    secondary = Color(0xFFF48FB1),  // Pinkish Tone
    tertiary = Color(0xFF66BB6A),   // Vibrant Green
    surface = Color(0xFF121212),    // Dark Background
    onSurface = Color(0xFFE0E0E0),  // Light Gray
    surfaceVariant = Color(0xFF424242) // Soft Gray
)

@Composable
fun ProjectMvvmImplTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
