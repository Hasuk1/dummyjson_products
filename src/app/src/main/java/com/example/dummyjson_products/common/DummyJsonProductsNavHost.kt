package com.example.dummyjson_products.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dummyjson_products.presentation.product_details.ProductDetailScreen
import com.example.dummyjson_products.presentation.products_list.ProductsListScreen

@Composable
fun DummyJsonProductsNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AppScreens.ProductsListScreen.route) {
      ProductsListScreen(navController)
    }
    composable(AppScreens.ProductDetailScreen.route + "/{productId}") {
      ProductDetailScreen(goBack = { navController.navigateUp() })
    }
  }
}