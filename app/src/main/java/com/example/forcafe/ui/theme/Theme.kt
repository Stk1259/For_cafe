package com.example.forcafe.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun ForCafeTheme(content:@Composable () -> Unit) {
 CompositionLocalProvider(
    localColorProvider provides lightpallet,
     content = content
    )
}

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = localColorProvider.current
}

val localColorProvider = staticCompositionLocalOf<Colors> {
    error("No default colors prov")
}