package com.example.dummyjson_products.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.dummyjson_products.common.AppScreens
import com.example.dummyjson_products.common.DummyJsonProductsNavHost
import com.example.dummyjson_products.presentation.main.components.GradientSurface
import com.example.dummyjson_products.presentation.ui.theme.DummyJsonProductsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      DummyJsonProductsTheme {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        GradientSurface {
          val navController = rememberNavController()
          DummyJsonProductsNavHost(navController, AppScreens.ProductsListScreen.route)
        }
      }
    }
  }
}