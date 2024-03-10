package com.example.dummyjson_products.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val LightColorScheme = lightColorScheme(
  primary = DarkBlueGray,
  inversePrimary = White,
  onPrimary = Color.Black,
  primaryContainer = LightGray,
  onPrimaryContainer = Color.Black,
  secondary = LightGray,
  onSecondary = Color.Black,
  secondaryContainer = White,
  onSecondaryContainer = LightDarkBlue,
  surface = CaribbeanGreen,
  inverseSurface = PurpleEggplant,
  scrim = LightBlue,
  error = Golden,
)

private val DarkColorScheme = darkColorScheme(
  primary = LightDarkBlue,
  inversePrimary = DarkBlue,
  onPrimary = White,
  primaryContainer = DarkLightBlue,
  onPrimaryContainer = White,
  secondary = DarkLightBlue,
  onSecondary = White,
  secondaryContainer = DarkBlue,
  onSecondaryContainer = DarkBlueGray,
  surface = PurpleEggplant,
  inverseSurface = CaribbeanGreen,
  scrim = RoyalBlue,
  error = Golden
)

@Composable
fun DummyJsonProductsTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
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
      (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
      ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme, typography = Typography, content = content
  )
}